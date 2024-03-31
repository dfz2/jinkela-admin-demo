<template>
  <div class="flex flex-wrap">
    <div class="mr-2 mb-2">
      <el-card class="!border-none " shadow="never">
        <template #header>
          <div class="card-header">
            <el-space>
              <el-dropdown>
                <el-button plain>
                  添加菜单<el-icon class="el-icon--right"><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>添加顶级菜单</el-dropdown-item>
                    <el-dropdown-item>添加子菜单</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
              <el-button :icon="Expand" plain>全部展开</el-button>
            </el-space>
          </div>
        </template>
        <el-input v-model="filterText" placeholder="输入菜单名称搜索" :suffix-icon="Search" />
        <div class="mt-4">
          <el-tree ref="treeRef" :data="menus" :filter-node-method="filterNode" show-checkbox node-key="id"
            style="min-width: 300px" :props="defaultProps" @check="handleTreeCheck" :check-strictly="true" />
        </div>
      </el-card>
    </div>
    <div class="flex-1"><el-card class="!border-none " shadow="never">
        <template #header>
          <div class="card-header">
            <div class="flex items-center">
              <el-icon :size="20">
                <Edit />
              </el-icon><span class="ml-2"> {{ titleText }}</span>
            </div>
          </div>
        </template>

        <div class="ml-20">
          <div class="mb-5" style="max-width: 600px">
            <el-alert title="With description" type="warning" description="从菜单列表选择一项后，进行编辑." />
          </div>
          <el-form :model="state" label-width="auto" style="max-width: 600px">
            <el-form-item label="类型">
              <el-radio-group v-model="state.type">
                <el-radio-button label="目录" value="M" />
                <el-radio-button label="菜单" value="C" />
                <el-radio-button label="按钮" value="A" />
              </el-radio-group>
            </el-form-item>
            <el-form-item label="父级菜单">
              <el-input v-model="state.name" />
            </el-form-item>
            <el-form-item label="标题">
              <el-input v-model="state.name" />
            </el-form-item>
            <el-form-item label="路径" v-if="state.type === 'C'">
              <el-input v-model="state.path" />
            </el-form-item>
            <el-form-item label="组件" v-if="state.type === 'C'">
              <el-select v-model="state.component" filterable placeholder="组件">
                <el-option v-for="item in components" :key="item.value" :label="item" :value="item" />
              </el-select>
            </el-form-item>
            <el-form-item label="选中菜单" v-if="state.type === 'C'">
              <el-input v-model="state.active" />
            </el-form-item>
            <el-form-item label="权限字符串" v-if="state.type !== 'M'">
              <el-input v-model="state.permission" />
            </el-form-item>
            <el-form-item label="隐藏菜单" v-if="state.type !== 'A'">
              <el-switch v-model="state.hidden" inline-prompt active-text="隐藏" inactive-text="显示" />
            </el-form-item>
            <el-form-item label="是否缓存" v-if="state.type !== 'A'">
              <el-switch v-model="state.keepAlive" inline-prompt active-text="是" inactive-text="否" />
            </el-form-item>
            <el-form-item label="备注">
              <el-input v-model="state.remark" type="textarea" />
            </el-form-item>
            <el-form-item label=" ">
              <el-button type="primary" @click="onSubmit">保存菜单信息</el-button>
              <el-button>Cancel</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>


</template>

<script lang="ts" setup>
import { Expand,  Search } from '@element-plus/icons-vue'
import { menuLists, menuEdit, menuDetail } from '@/api/menu';
import { reactive, ref, watch, computed } from 'vue';
import { getModulesKey } from '@/router';
import { ElMessage } from 'element-plus';

const defaultProps = {
  children: 'children',
  label: 'name',
}

const treeRef = ref()
const filterText = ref<string>('')
const jinkelaMenuId = ref('')
const menus = ref([])
const components = ref<Array<any>>([{}])


const state = reactive({
  id: '',
  parentId: '',
  name: '',
  type: 'M',
  icon: '',
  permission: '',
  hidden: false,
  path: '',
  component: '',
  arguments: '',
  keepAlive: '',
  enabled: '',
  active: '',
  remark: '',
})

watch(filterText, (val) => {
  treeRef.value.filter(val)
})

const titleText = computed(() => jinkelaMenuId.value != '' ? "编辑菜单" : "新建菜单")


const getLocalModulesKey = () => {
  components.value = getModulesKey()
}

const filterNode = (value: string, data: any) => {
  if (!value) return true
  return data.name.includes(value)
}

const reset = () => {
  state.id = ''
  state.parentId = ''
  state.name = ''
  state.type = 'M'
  state.icon = ''
  state.permission = ''
  state.hidden = false
  state.path = ''
  state.component = ''
  state.arguments = ''
  state.keepAlive = ''
  state.enabled = ''
  state.active = ''
  state.remark = ''
  jinkelaMenuId.value = ''
}

const handleTreeCheck = async (data: API.JinkelaMenu, list: any) => {
  const res: any = await menuDetail({id: data.id})
  Object.assign(state, res)
  jinkelaMenuId.value = data.id
  const length = list.checkedKeys.length
  if (length == 2) {
    treeRef.value.setCheckedKeys([data.id]);
  }

  if (length == 0) {
    reset()
  }

}

const onSubmit = async () => {
  const json = { ...state };
  console.log(json);
  menuEdit(json)
  console.log('submit!')
  ElMessage.success("保存成功")
}


const getMenuLists = async () => {
  const res: any = await menuLists()
  menus.value = res
}

getMenuLists()
getLocalModulesKey()
</script>

<style lang="scss" scoped>
.card-header {
  height: 30px;
}
</style>