<template>

  <el-card class="!border-none" shadow="never">

    <div aria-label="A complete example of page header">
      <el-page-header @back="onBack">
        <template #breadcrumb>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: './page-header.html' }">
              homepage
            </el-breadcrumb-item>
            <el-breadcrumb-item><a href="./page-header.html">route 1</a></el-breadcrumb-item>
            <el-breadcrumb-item>route 2</el-breadcrumb-item>
          </el-breadcrumb>
        </template>
        <template #content>
          <div class="flex items-center">
            <el-avatar class="mr-3" :size="32"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
            <span class="text-large font-600 mr-3"> Title </span>
            <span class="text-sm mr-2" style="color: var(--el-text-color-regular)">
              Sub title
            </span>
            <el-tag>Default</el-tag>
          </div>
        </template>
        <template #extra>
          <div class="flex items-center">
            <el-button>Print</el-button>
            <el-button type="primary" class="ml-2">Edit</el-button>
          </div>
        </template>
        <div class="m-20 mt-10">
          <div class="mt-3">
            <el-tree ref="treeRef" node-key="id" :data="state.menus" :props="state.treeProps" :default-expand-all="true"
              show-checkbox />
          </div>
        </div>
      </el-page-header>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { menuLists } from '@/api/menu';
import { reactive } from 'vue';
import { useRouter } from "vue-router";

const router = useRouter()

const state = reactive({
  treeProps: {
    label: 'name',
    children: 'children'
  },
  menus: []
})

const getLists = async () => {
  const data = await menuLists()
  state.menus = data
}

const onBack = () => {
  router.back()
}

getLists()
</script>

<style lang="scss" scoped></style>