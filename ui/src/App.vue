<script lang="ts" setup>
import { ref, computed } from 'vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
const language = ref<string>('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))
</script>

<template>
  <el-config-provider :locale="locale">
    <router-view/>
  </el-config-provider>
</template>

<style>
body {
  margin: 0;
  padding: 0;
  background-color: #F2F3F5;
}
</style>
