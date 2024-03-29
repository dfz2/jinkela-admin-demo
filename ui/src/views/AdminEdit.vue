<template>
    <div class="edit-popup">
        <Popup ref="popupRef" :title="popupTitle" :async="true" width="550px" @confirm="handleSubmit"
            @close="handleClose">
            <el-form ref="formRef" :model="formData" label-width="84px" :rules="formRules">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="formData.username" :disabled="isRoot" placeholder="请输入账号" clearable />
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="formData.nickname" placeholder="请输入昵称" clearable />
                </el-form-item>
            </el-form>
        </Popup>
    </div>
</template>



<script lang="ts" setup>
import Popup from '@/components/Popup.vue'
import { adminAdd, adminEdit, adminDetail } from '@/api/admin'
import { shallowRef, computed, ref, reactive } from 'vue'
import { ElMessage } from 'element-plus';

const emit = defineEmits(['success', 'close'])

const formRef = shallowRef(null)
const popupRef = shallowRef(null)
const mode = ref('add')
const popupTitle = computed(() => mode.value == 'edit' ? '编辑管理员' : '新增管理员')

const formData = reactive({
    id: 0,
    username: '',
    nickname: '',
    avatar: '',
})

const isRoot = computed(() => formData.id == 1000000 || mode.value == 'edit')

const formRules = reactive({
    username: [
        {
            required: true,
            message: '请输入账号',
            trigger: ['blur']
        }
    ],
    nickname: [
        {
            required: true,
            message: '请输入名称',
            trigger: ['blur']
        }
    ],
})

const handleSubmit = async () => {
    await formRef.value?.validate()
    mode.value == 'edit' ? await adminEdit(formData) : await adminAdd(formData)
    popupRef.value?.close()

    ElMessage.success('操作成功')
    emit('success')
}

const open = (type = 'add') => {
    mode.value = type
    popupRef.value?.open()
}

const setFormData = async (row) => {
    const data = await adminDetail({
        id: row.id
    })
    for (const key in formData) {
        if (data[key] != null && data[key] != undefined) {
            //@ts-ignore
            formData[key] = data[key]
        }
    }
}

const handleClose = () => {
    emit('close')
}

defineExpose({
    open,
    setFormData
})
</script>
<style>
.form-tips {
    margin-top: 4px;
    font-size: var(--el-font-size-extra-small);
    line-height: 24px;
    color: var(--el-text-color-secondary);
}
</style>