import { createApp } from 'vue'

import './style.css'
import 'element-plus/es/components/message/style/css'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './stores'
import './permission'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)

app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}


app.use(store)
app.use(router)
app.mount('#app')

