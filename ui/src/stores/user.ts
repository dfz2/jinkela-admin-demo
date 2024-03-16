import { defineStore } from 'pinia'
import { filterAsyncRoutes } from '@/router'
import { getUserinfoApi, getMenusApi } from "@/api/user";


export const useUserStore = defineStore('userStore', {
  state: () => ({
    userInfo: {},
    routes: [],
    menu: [],
    perms: []
  }),
  actions: {
    getUserInfo() {
      return new Promise((resolve, reject) => {
        getUserinfoApi({})
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
    getRoutes() {
      return new Promise((resolve, reject) => {
        getMenusApi({})
          .then((data) => {
            this.menu = data
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