<script lang="ts" setup>
import { adminLists, adminDelete } from '@/api/admin'
import { usePaging } from '@/hooks/usePaging'
import Pagination from '@/components/Pagination.vue'
import AdminEditPopup from './AdminEdit.vue'
import { shallowRef, reactive, ref, onMounted, nextTick } from 'vue'
import { Plus } from "@element-plus/icons-vue"
import { ElMessage, ElMessageBox } from "element-plus"

const editRef = shallowRef<InstanceType<typeof AdminEditPopup>>()
const showEdit = ref<boolean>(false)
const queryFrom = reactive<Partial<API.JinkelaUser>>({
  username: ''
})

const { pager, getLists, resetParams, resetPage } = usePaging({
  fetchFun: adminLists,
  params: queryFrom
})


const handleAdd = async () => {
  showEdit.value = true
  await nextTick()
  editRef.value?.open('add')
}

const handleEdit = async (data: API.JinkelaUser) => {
  showEdit.value = true
  await nextTick()
  editRef.value?.open('edit')
  editRef.value?.setFormData(data)
}

const handleDelete = async (id: string) => {
  await ElMessageBox.confirm('确定要删除？', '温馨提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'info'
  })
  await adminDelete({ id })
  ElMessage.success('删除成功')
  getLists()
}


onMounted(() => {
  getLists()
})



</script>

<template>
  <div class="admin">
    <el-card class="!border-none" shadow="never">
      <el-form class="mb-[-16px]" :model="queryFrom" inline>
        <el-form-item label="管理员账号">
          <el-input v-model="queryFrom.username" class="w-[280px]" clearable @keyup.enter="resetPage" />
        </el-form-item>
        <el-form-item label="管理员名称">
          <el-input v-model="queryFrom.nickname" class="w-[280px]" clearable @keyup.enter="resetPage" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="resetPage">查询</el-button>
          <el-button @click="resetParams">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="pager.loading" class="mt-4 !border-none" shadow="never">
      <el-button v-perms="['xinzhengguanliyuan']" type="primary" @click="handleAdd" :icon="Plus">
        新增
      </el-button>
      <div class="mt-4">
        <el-table :data="pager.lists" size="large">
          <el-table-column label="ID" prop="id" />
          <el-table-column label="登录账号" prop="username" />
          <el-table-column label="昵称" prop="nickname" />
          <el-table-column label="状态" prop="enabled" />
          <el-table-column label="创建时间" prop="createdAt" min-width="120" />
          <el-table-column label="操作" min-width="120" fixed="right">
            <template #default="{ row }">
              <el-button v-perms="['gengxinguanliyuan']" type="primary" link @click="handleEdit(row)">
                编辑
              </el-button>
              <el-button v-if="row.id != 1000000" v-perms="['shanchuguanliyuan']" type="danger" link
                @click="handleDelete(row.id)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="flex mt-4 justify-end">
        <Pagination v-model="pager" @change="getLists" />
      </div>
    </el-card>

    <AdminEditPopup v-if="showEdit" ref="editRef" @success="getLists" @close="showEdit = false" />
  </div>
</template>