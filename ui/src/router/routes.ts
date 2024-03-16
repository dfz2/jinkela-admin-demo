import Layout from '@/components/Layout.vue'
import { type RouteRecordRaw } from 'vue-router'

export const LAYOUT = () => Promise.resolve(Layout)
export const INDEX_ROUTE_NAME = Symbol()
export const INDEX = '/'


export const routes: RouteRecordRaw[] = [
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/Notfound.vue')
  },
  {
    path: '/forbidden',
    component: () => import('@/views/Forbidden.vue')
  },
  {
    path: '/login',
    component: () => import('@/views/Login.vue'),
    meta: {
      title: '登录'
    }
  },
  {
    path: '/user',
    component: LAYOUT,
    children: [
      {
        path: 'profile',
        component: () => import('@/views/UserProfile.vue'),
      },
    ]
  },
]

export const INDEX_ROUTE: RouteRecordRaw = {
  path: INDEX,
  component: LAYOUT,
  name: INDEX_ROUTE_NAME
}