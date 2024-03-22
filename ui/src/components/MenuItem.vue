<script setup>
import { computed, } from 'vue';
import { isExternal } from '@/router';

const props = defineProps({
  route: {},
  path: ''
})

const routePath = computed(() => props.path)

const hasShowChild = computed(() => {
  const children = props.route.children ?? []
  return !!children.filter((item) => !item.meta?.hidden).length
})

const resolvePath = (path) => {
  return isExternal(path) ? path : getNormalPath(`${props.path}/${path}`)
}

const getNormalPath = (path) => {
  if (path.length === 0 || !path || path == 'undefined') {
    return path
  }
  const newPath = path.replace('//', '/')
  const length = newPath.length
  if (newPath[length - 1] === '/') {
    return newPath.slice(0, length - 1)
  }
  return newPath
}

</script>


<template>
  <template v-if="!route.meta?.hidden">
    <router-link v-if="!hasShowChild" :to="`${routePath}`">
      <el-menu-item :index="routePath">
        <el-icon>
          <component :is="route.meta.icon" />
        </el-icon>
        <template #title>{{ route.meta.title }}</template>
      </el-menu-item>
    </router-link>
    <el-sub-menu v-else :index="routePath">
      <template #title>
        <el-icon>
          <component :is="route.meta.icon" />
        </el-icon>
        <span>{{ route.meta?.title }}</span>
      </template>
      <MenuItem :path="resolvePath(children.path)" :key="resolvePath(children.path)" :route="children"
        v-for="children in route.children">
      </MenuItem>
    </el-sub-menu>
  </template>

</template>
