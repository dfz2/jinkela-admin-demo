import { defineStore } from 'pinia'
import { filterAsyncRoutes } from '@/router'
import { getUserinfoApi, getMenusApi } from "@/api/user";
import { type RouteRecordRaw } from 'vue-router'

interface UserState {
  userInfo: API.JinkelaUser;
  routes: RouteRecordRaw[];
  menus: API.JinkelaMenu[];
  perms: any[];
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
          .then((data: any) => {
            this.userInfo = data
            this.perms = data.authorities
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