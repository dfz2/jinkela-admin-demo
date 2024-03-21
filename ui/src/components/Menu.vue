<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { useUserStore } from '@/stores/user';
import MenuItem from './MenuItem.vue';

const route = useRoute()
const userStore = useUserStore()
const activeMenu = computed(() => route.meta?.active || route.path)
const routes = computed(() => userStore.routes)
</script>

<template>
  <el-menu :default-active="activeMenu">
    <MenuItem :path="route.path" :key="route.path" :route="route" v-for="route in routes" />
  </el-menu>
</template>