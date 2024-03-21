<template>
    <div class="edit-popup">
        <Popup ref="popupRef" :title="popupTitle" :async="true" width="550px" @confirm="handleSubmit"
            @close="handleClose">
            <el-form ref="formRef" :model="formData" label-width="84px" :rules="formRules">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="formData.username" :disabled="isRoot" placeholder="请输入账号" clearable />
                </el-form-item>

                <el-form-item label="名称" prop="nickname">
                    <el-input v-model="formData.nickname" placeholder="请输入名称" clearable />
                </el-form-item>
            </el-form>
        </Popup>
    </div>
</template>
<script setup>
import Popup from '@/components/Popup.vue'
import { adminAdd, adminEdit, adminDetail } from '@/api/admin'
import { useDictOptions } from '@/hooks/useDictOptions'
// import { roleAll } from '@/api/role'
// import { postAll } from '@/api/post'
// import { deptLists } from '@/api/department'
import { shallowRef, computed, ref, reactive, defineExpose } from 'vue'

// import feedback from '@/utils/feedback'


const emit = defineEmits(['success', 'close'])
const formRef = shallowRef(null)
const popupRef = shallowRef(null)
const mode = ref('add')
const popupTitle = computed(() => {
    return mode.value == 'edit' ? '编辑管理员' : '新增管理员'
})

const formData = reactive({
    id: 0,
    username: '',
    nickname: '',
    avatar: '',
})

const isRoot = computed(() => {
    return formData.id == 1
})

const passwordConfirmValidator = (rule, value, callback) => {
    if (formData.password) {
        if (!value) callback(new Error('请再次输入密码'))
        if (value !== formData.password) callback(new Error('两次输入密码不一致!'))
    }
    callback()
}
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


// const { optionsData } = useDictOptions({
//     role: {
//         api: roleAll,
//     },
//     // post: {
//     //     api: postAll
//     // },
//     // dept: {
//     //     api: deptLists
//     // }
// })

const handleSubmit = async () => {
    await formRef.value?.validate()
    mode.value == 'edit' ? await adminEdit(formData) : await adminAdd(formData)
    popupRef.value?.close()
    feedback.msgSuccess('操作成功')
    emit('success')
}

const open = (type = 'add') => {
    mode.value = type
    popupRef.value?.open()
}

const setFormData = async (row) => {
    formRules.password = []
    formRules.passwordConfirm = [
        {
            validator: passwordConfirmValidator,
            trigger: 'blur'
        }
    ]
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
