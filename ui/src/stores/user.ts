import { defineStore } from 'pinia'
import { filterAsyncRoutes } from '@/router'
import { getUserinfoApi, getMenusApi } from "@/api/user";
import { type RouteRecordRaw } from 'vue-router'

interface UserState {
  userInfo: any;
  routes: RouteRecordRaw[];
  menus: API.Menu[];
  perms: string[];
}


export const useUserStore = defineStore('userStore', {
  state: (): UserState => ({
    userInfo: {},
    routes: [],
    menus: [],
    perms: []
  }),
  actions: {
    getUserInfo() {
      return new Promise((resolve, reject) => {
        getUserinfoApi()
          .then((data) => {
            this.userInfo = data
            this.perms = ["*"]
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    getRoutes(jinkelaUserId: string) {
      return new Promise((resolve, reject) => {
        getMenusApi(jinkelaUserId)
          .then((data: any) => {
            this.menus = data
            this.routes = filterAsyncRoutes(data)
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    }
  },
})