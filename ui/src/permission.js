
import router, { findFirstValidRoute } from '@/router'
import { useUserStore } from '@/stores/user'
import { INDEX_ROUTE, INDEX_ROUTE_NAME, INDEX } from './router/routes'
// NProgress配置
// NProgress.configure({ showSpinner: false })

// 免登录白名单
const whiteList = ['/login', '/forbidden']

router.beforeEach(async (to, _from, next) => {
  // 开始 Progress Bar
  // NProgress.start()
  // document.title = to.meta.title

  // const tabsStore = useTabsStore()
  if (whiteList.includes(to.path)) {
    // 在免登录白名单，直接进入
    next()
  } else {
    const userStore = useUserStore()
    // 获取用户信息
    const hasGetUserInfo = Object.keys(userStore.userInfo).length !== 0
    if (hasGetUserInfo) {
      if (to.path === '/login') {
        next({ path: INDEX })
      } else {
        next()
      }
    } else {
      try {
        await userStore.getUserInfo()
        await userStore.getRoutes(userStore.userInfo.id)
        const routes = userStore.routes
        // 找到第一个有效路由
        const routeName = findFirstValidRoute(routes)

        // 没有有效路由跳转到403页面
        if (!routeName) {
          // clearAuthInfo()
          next('/forbidden')
          return
        }

        // tabsStore.setRouteName(routeName!)
        INDEX_ROUTE.redirect = { name: routeName }
        // 动态添加index路由
        router.addRoute(INDEX_ROUTE)

        routes.forEach((route) => {
          // https 则不插入
          // if (isExternal(route.path)) {
          //     return
          // }
          if (!route.children) {
            router.addRoute(INDEX_ROUTE_NAME, route)
            return
          }
          // 动态添加可访问路由表
          router.addRoute(route)
        })

        next({ ...to, replace: true })
      } catch (err) {
        // clearAuthInfo()
        next({ path: '/login', query: { continue: to.fullPath } })
      }
    }
  }
})

