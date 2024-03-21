<script setup name="admin">
import { adminLists, adminDelete, adminStatus } from '@/api/admin'
import { usePaging } from '@/hooks/usePaging'
import Pagination from '@/components/Pagination.vue'
import AdminEdit from './AdminEdit.vue'
import { shallowRef, reactive, ref, onMounted, nextTick } from 'vue'
import { Plus } from "@element-plus/icons-vue"
import { ElMessage, ElMessageBox } from "element-plus"

const editRef = shallowRef(null)
const showEdit = ref(false)

const formData = reactive({
  username: null,
  nickname: null,
  role: null
})


const { pager, getLists, resetParams, resetPage } = usePaging({
  fetchFun: adminLists,
  params: formData
})

const changeStatus = async (active, id) => {
  try {
    await ElMessageBox.confirm(`确定${active ? '停用' : '开启'}当前管理员？`, '温馨提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'info'
    })

    await adminStatus({ id })
    ElMessage.success('修改成功')
    getLists()
  } catch (error) {
    getLists()
  }
}


const handleAdd = async () => {
  showEdit.value = true
  await nextTick()
  editRef.value?.open('add')
}

const handleEdit = async (data) => {
  showEdit.value = true
  await nextTick()
  editRef.value?.open('edit')
  editRef.value?.setFormData(data)
}

const handleDelete = async (id) => {
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
      <el-form class="mb-[-16px]" :model="formData" inline>
        <el-form-item label="管理员账号">
          <el-input v-model="formData.username" class="w-[280px]" clearable @keyup.enter="resetPage" />
        </el-form-item>
        <el-form-item label="管理员名称">
          <el-input v-model="formData.nickname" class="w-[280px]" clearable @keyup.enter="resetPage" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="resetPage">查询</el-button>
          <el-button @click="resetParams">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="pager.loading" class="mt-4 !border-none" shadow="never">
      <el-button v-perms="['system:admin:add']" type="primary" @click="handleAdd" :icon="Plus">
        新增
      </el-button>
      <div class="mt-4">
        <el-table :data="pager.lists" size="large">
          <el-table-column label="ID" prop="id" min-width="60" />
          <el-table-column label="登录账号" prop="username" min-width="100" />
          <el-table-column label="昵称" prop="nickname" min-width="100" />
          <el-table-column label="状态" prop="enabled" min-width="120" />
          <el-table-column label="创建时间" prop="createdAt" min-width="180" />
          <el-table-column label="最近登录时间" prop="lastLoginTime" min-width="180" />
          <el-table-column label="最近登录IP" prop="lastLoginIp" min-width="120" />
          <el-table-column label="操作" width="120" fixed="right">
            <template #default="{ row }">
              <el-button v-perms="['system:admin:edit']" type="primary" link @click="handleEdit(row)">
                编辑
              </el-button>
              <el-button v-if="row.id != 1" v-perms="['system:admin:del']" type="danger" link
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

    <AdminEdit v-if="showEdit" ref="editRef" @success="getLists" @close="showEdit = false" />
  </div>
</template>
@/composables/usePaging