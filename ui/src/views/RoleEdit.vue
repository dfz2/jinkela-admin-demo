<script setup>
import { roleAdd, roleDetail, roleEdit } from '@/api/role'
import Popup from '@/components/Popup.vue'
import { ElMessage } from 'element-plus';
// import feedback from '@/utils/feedback'
import { ref, shallowRef, reactive, computed, defineExpose } from 'vue'

const emit = defineEmits(['success', 'close'])
const formRef = shallowRef()
const popupRef = shallowRef(null)
const mode = ref('add')
const popupTitle = computed(() => {
  return mode.value == 'edit' ? '编辑角色' : '新增角色'
})


const state = reactive({
  id: '',
  name: '',
  remark: '',
  sort: 0,
  version: null,
  enabled: 0,
  menus: []
})

const rules = {
  name: [
    {
      required: true,
      message: '请输入名称',
      trigger: ['blur']
    }
  ]
}

const handleSubmit = async () => {
  await formRef.value?.validate()
  const params = { ...state, menuIds: state.menus.join() }
  mode.value == 'edit' ? await roleEdit(params) : await roleAdd(params)
  popupRef.value?.close()
  ElMessage.success('操作成功')
  emit('success')
}

const handleClose = () => {
  emit('close')
}

const open = (type = 'add') => {
  mode.value = type
  popupRef.value?.open()
}

const setFormData = async (row) => {
  const data = await roleDetail({
    id: row.id
  })
  for (const key in state) {
    if (data[key] != null && data[key] != undefined) {
      //@ts-ignore
      state[key] = data[key]
    }
  }
}

defineExpose({
  open,
  setFormData
})


</script>

<template>
  <div class="edit-popup">
    <Popup ref="popupRef" :title="popupTitle" :async="true" width="500px" @confirm="handleSubmit" @close="handleClose">
      <el-form class="ls-form" ref="formRef" :rules="rules" :model="state" label-width="80px">
        <el-form-item label="角色名称" prop="name">
          <el-input class="ls-input" v-model="state.name" maxlength="100"  placeholder="请输入名称" clearable />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="state.remark" type="textarea" :autosize="{ minRows: 4, maxRows: 6 }" placeholder="请输入备注"
            maxlength="200" show-word-limit />
        </el-form-item>
      </el-form>
    </Popup>
  </div>
</template>
