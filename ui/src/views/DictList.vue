<script lang="ts" setup>
import { dictTypeDelete, dictTypeLists, dictDataLists } from '@/api/dict'
import { nextTick, shallowRef, ref, reactive } from 'vue'
// import EditPopup from './edit.vue'
import { Delete, Plus, Search } from "@element-plus/icons-vue";

const editRef = shallowRef(null)
const showEdit = ref(false)
const currentRow = ref({})
const selectData = ref([])

const state = reactive({
  dictTypeLists: [],
  dictDataLists: [],
  dictType: '0',
  dictName: '',
})

const handleCurrentChange = (val) => {
  currentRow.value = val
  state.dictType = val?.id
  getDictDataLists()
  console.log(val)
}

const getDictTypeLists = () => {
  dictTypeLists({}).then(res => {
    state.dictTypeLists = res
  })
}

const getDictDataLists = () => {
  dictDataLists({ type: state.dictType }).then(res => {
    state.dictDataLists = res
  })
}


const handleSelectionChange = (val) => {
  selectData.value = val.map(({ id }) => id)
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

// 删除角色
const handleDelete = async (ids) => {
  await ElMessageBox.confirm('确定要删除？', '温馨提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  await dictTypeDelete({ ids })
  ElMessage.success('删除成功')
}

getDictTypeLists()
getDictDataLists()
</script>


<template>
  <div class="dict-type">
    <el-card class="!border-none table-result" shadow="never">
      <div class="flex flex-row ">
        <div class="w-80  m-4 mt-10">
          <div class="flex flex-col">
            <div class="">
              <el-input placeholder="请输入字典名称搜索" v-model="state.dictName" :prefix-icon="Search" />
            </div>
            <div class="mt-2">
              <el-button v-perms="['setting:dict:type:add']" type="primary" @click="handleAdd" :icon="Plus">
                新增
              </el-button>
              <el-button v-perms="['setting:dict:type:list']" :disabled="!selectData.length" type="danger"
                @click="handleDelete(selectData)" :icon="Delete">
                删除
              </el-button>
            </div>
          </div>

          <div class="mt-4">
            <el-table :data="state.dictTypeLists" size="large" :show-header="false" highlight-current-row
              @current-change="handleCurrentChange">
              <el-table-column prop="dictName">
                <template #default="scope">
                  {{ `${scope.row.dictName}(${scope.row.dictType})` }}
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
        <div class="border-l-2 flex-1">

          <div class="dict-type m-4 mt-10">

            <div class="flex flex-col">

              <div>
                <el-form ref="formRef" class="mb-[-16px]" :model="state" inline>
                  <el-form-item label="数据名称">
                    <el-input class="w-[280px]" v-model="state.dictType" clearable />
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary">查询</el-button>
                    <el-button>重置</el-button>
                  </el-form-item>
                </el-form>
              </div>

              <div class="mt-2">
                <el-button v-perms="['setting:dict:data:add']" type="primary" @click="handleAdd" :icon="Plus">
                  添加数据
                </el-button>
                <el-button v-perms="['setting:dict:data:del']" :disabled="!selectData.length" type="danger"
                  @click="handleDelete(selectData)" :icon="Delete">
                  删除
                </el-button>
              </div>

            </div>

            <div class="mt-4">
              <div>
                <el-table :data="state.dictDataLists" size="large" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" />
                  <el-table-column label="ID" prop="id" />
                  <el-table-column label="数据名称" prop="name" />
                  <el-table-column label="数据值" prop="value" />
                  <el-table-column label="状态">
                    <template v-slot="{ row }">
                      <el-tag v-if="row.status == 1">正常</el-tag>
                      <el-tag v-else type="danger">停用</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="备注" prop="remark" min-width="120" show-tooltip-when-overflow />
                  <el-table-column label="排序" prop="sort" />
                  <el-table-column label="操作" width="140" fixed="right">
                    <template #default="{ row }">
                      <el-button v-perms="['setting:dict:data:edit']" link type="primary" @click="handleEdit(row)">
                        编辑
                      </el-button>
                      <el-button v-perms="['setting:dict:data:del']" link type="danger" @click="handleDelete([row.id])">
                        删除
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </div>
          </div>
        </div>
      </div>

    </el-card>


    <!-- <edit-popup v-if="showEdit" ref="editRef" @success="getDictTypeLists" @close="showEdit = false"/> -->


  </div>
</template>

<style scoped>
:deep(.table-result .el-card__body) {
  padding: 0;
}

:deep(.el-table__body tr.current-row>td) {
  background-color: #c6e2ff !important;
  color: red !important;

}
</style>
@/api/dict.js