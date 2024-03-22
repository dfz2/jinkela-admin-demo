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

        <el-form class="ls-form" ref="formRef" :rules="rules" :model="formData" label-width="60px">
          <el-form-item prop="menus" class="mt-10 ml-20">
            <div>
              <el-checkbox label="展开/折叠" @change="handleExpand" />
              <el-checkbox label="全选/不全选" @change="handleSelectAll" />
              <el-checkbox v-model="checkStrictly" label="父子联动" />
              <div>
                <el-tree ref="treeRef" :data="state.menus" :props="state.treeProps" :check-strictly="!checkStrictly"
                  node-key="id" :default-expand-all="isExpand" show-checkbox />
              </div>
            </div>
          </el-form-item>
        </el-form>


      </el-page-header>
    </div>
  </el-card>
</template>

<script setup>
import { reactive } from 'vue'
import { ElNotification as notify } from 'element-plus'
import { menuDelete, menuLists } from '@/api/menu'

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
  notify('Back')
}

getLists()
</script>

<style lang="scss" scoped></style>