

import { createRouter, createWebHistory, RouterView, useRouter, type RouteRecordRaw } from 'vue-router'
import { LAYOUT, INDEX_ROUTE_NAME, routes } from "./routes"
import { useUserStore } from '@/stores/user'


// 匹配views里面所有的.vue文件，动态引入
const modules = import.meta.glob('/src/views/**/*.vue')

export function isExternal(path: string) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

//
export function getModulesKey() {
  return Object.keys(modules).map((item) => item.replace('/src/views/', '').replace('.vue', ''))
}

// 过滤路由所需要的数据
export function filterAsyncRoutes(menus: Array<API.Menu>, firstRoute: boolean = true): RouteRecordRaw[]  {
  return menus.map((route) => {
    const routeRecord = createRouteRecord(route, firstRoute)
    if (route.children != null && route.children && route.children.length) {
      routeRecord.children = filterAsyncRoutes(route.children, false)
    }
    return routeRecord
  })
}


// 创建一条路由记录
export function createRouteRecord(menu: API.Menu, firstRoute: boolean): RouteRecordRaw {
  //@ts-ignore
  const routeRecord: RouteRecordRaw = {
    path: isExternal(menu.path) ? menu.path : firstRoute ? `/${menu.path}` : menu.path,
    name: menu.component,
    meta: {
      hidden: menu.hidden,
      keepAlive: menu.keepAlive,
      title: menu.name,
      perms: menu.permission,
      query: menu.arguments,
      icon: menu.icon,
      type: menu.type,
      active: menu.active
    }
  }
  switch (menu.type) {
    case "M":
      routeRecord.component = firstRoute ? LAYOUT : RouterView
      if (!menu.children) {
        routeRecord.component = RouterView
      }
      break
    case "C":
      routeRecord.component = loadRouteView(menu.component)
      break
  }
  return routeRecord
}

// 动态加载组件
export function loadRouteView(component: string) {
  try {
    const key = Object.keys(modules).find((key) => {
      return key.includes(`${component}.vue`)
    })
    if (key) {
      return modules[key]
    }
    throw Error(`找不到组件${component}，请确保组件路径正确`)
  } catch (error) {
    console.error(error)
    return RouterView
  }
}

// 找到第一个有效的路由
export function findFirstValidRoute(routes: Array<RouteRecordRaw>): string | undefined {
  for (const route of routes) {
    if (route.meta?.type == "C" && !route.meta?.hidden && !isExternal(route.path)) {
      return route.name as string
    }
    if (route.children) {
      const name = findFirstValidRoute(route.children)
      if (name) {
        return name
      }
    }
  }
}

//通过权限字符查询路由路径
export function getRoutePath(perms: string) {
  const routerObj = useRouter() || router
  return routerObj.getRoutes().find((item) => item.meta?.perms == perms)?.path || ''
}

// 重置路由
export function resetRouter() {
  router.removeRoute(INDEX_ROUTE_NAME)
  const { routes } = useUserStore()
  routes.forEach((route) => {
    const name = route.name as string
    if (name && router.hasRoute(name)) {
      router.removeRoute(name)
    }
  })
}

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
})


export default router