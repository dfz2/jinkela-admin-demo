<script setup name="menu">
import { menuDelete, menuLists } from '@/api/menu'
// import EditPopup from './edit.vue'
import { Plus } from "@element-plus/icons-vue";
import { shallowRef, ref, reactive, computed, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus';

const tableRef = shallowRef(null)
const editRef = shallowRef(null)
let isExpand = false
const loading = ref(false)
const showEdit = ref(false)
const lists = ref([])

const getLists = async () => {
    loading.value = true
    try {
        const data = await menuLists()
        lists.value = data
        loading.value = false
    } catch (error) {
        loading.value = false
    }
}

const handleAdd = async (id) => {
    showEdit.value = true
    await nextTick()
    if (id) {
        editRef.value?.setFormData({
            pid: id
        })
    }
    editRef.value?.open('add')
}

const handleEdit = async (data) => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('edit')
    editRef.value?.getDetail(data)
}

const handleDelete = async (id) => {
    await feedback.confirm('确定要删除？')
    await menuDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

const handleExpand = () => {
    isExpand = !isExpand
    toggleExpand(lists.value, isExpand)
}

const toggleExpand = (children, unfold = true) => {
    for (const key in children) {
        tableRef.value?.toggleRowExpansion(children[key], unfold)
        if (children[key].children) {
            toggleExpand(children[key].children, unfold)
        }
    }
}

getLists()
</script>


<template>
    <div class="menu-lists">
        <el-card class="!border-none" shadow="never">
            <div>
                <el-button v-perms="['system:menu:add']" type="primary" @click="handleAdd()" :icon="Plus">
                    新增
                </el-button>
                <el-button @click="handleExpand"> 展开/折叠 </el-button>
            </div>
            <el-table v-loading="loading" ref="tableRef" class="mt-4" size="large" :data="lists" row-key="id"
                :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
                <el-table-column label="菜单名称" prop="name" min-width="150" show-overflow-tooltip />
                <el-table-column label="类型" prop="type" min-width="80">
                    <template #default="{ row }">
                        <div v-if="row.type == 'M'">目录</div>
                        <div v-else-if="row.type == 'C'">菜单</div>
                        <div v-else-if="row.type == 'A'">按钮</div>
                    </template>
                </el-table-column>
                <el-table-column label="图标" prop="icon" min-width="80">
                    <template #default="{ row }">
                        <div class="flex">
                            <icon :name="row.icon" :size="20" />
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="权限标识" prop="permission" min-width="150" show-overflow-tooltip />
                <el-table-column label="状态" prop="enabled" min-width="100">
                    <template #default="{ row }">
                        <el-tag v-if="row.enabled == 0">正常</el-tag>
                        <el-tag v-else type="danger">停用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="排序" prop="sort" min-width="100" />
                <el-table-column label="更新时间" prop="modifiedAt" min-width="180"></el-table-column>
                <el-table-column label="操作" width="220" fixed="right">
                    <template #default="{ row }">
                        <el-button v-if="row.menuType !== 'A'" v-perms="['system:menu:add']" type="primary" link
                            @click="handleAdd(row.id)">
                            新增
                        </el-button>
                        <el-button v-perms="['system:menu:edit']" type="primary" link @click="handleEdit(row)">
                            编辑
                        </el-button>
                        <el-button v-perms="['system:menu:del']" type="danger" link @click="handleDelete(row.id)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <edit-popup v-if="showEdit" ref="editRef" @success="getLists" @close="showEdit = false" />
    </div>
</template>
