import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'
import 'element-plus/es/components/message/style/css'
import { createApp } from 'vue'
import App from './App.vue'
import './permission'
import router from './router'
import store from './stores'
import './style.css'
import { useUserStore } from './stores/user'


const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}


app.directive('perms', {
  mounted: (el: HTMLElement, binding: any) => {
    const { value } = binding
    const userStore = useUserStore()
    const permissions = userStore.perms
    const all_permission = '*'
    if (Array.isArray(value)) {
      if (value.length > 0) {
        const hasPermission = permissions.some((key: any) => {
          return all_permission == key || value.includes(key.authority)
        })
        if (!hasPermission) {
          el.parentNode && el.parentNode.removeChild(el)
        }
      }
    } else {
      throw new Error('like v-perms="[xxx]"')
    }
  }
})

app.use(store)
app.use(router)
app.mount('#app')

