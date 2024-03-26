<script setup>
import { useUserStore } from '@/stores/user';
import { MoreFilled, SwitchButton, User, UserFilled } from '@element-plus/icons-vue'
import { computed } from 'vue';
import { logoutApi } from "@/api/user.js";
import { ElMessageBox } from "element-plus";



const userStore = useUserStore()
const userInfo = computed(() => userStore.userInfo)
const handleCommand = async (command) => {


  if (command == 'logout') {
    await ElMessageBox.confirm('确定退出登录吗？', '温馨提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await logoutApi()
    userStore.userInfo = {}
    window.location.reload()
  }

}
</script>


<template>
  <div class="flex-row flex justify-center items-center">
    <div class="flex flex-1">
      <div class="logo"></div>
    </div>
    <div class="flex flex-row h-full">
      <div class="flex mr-3">
        <el-dropdown @command="handleCommand">
          <div class="flex justify-center items-center">
            <el-avatar :size="30" :src="userInfo.avatar" :icon="UserFilled" />
            <div class="ml-1 text-white">{{ userInfo.nickname }}</div>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <router-link to="/user/profile">
                <el-dropdown-item :icon="User" command="profile">个人中心</el-dropdown-item>
              </router-link>
              <el-dropdown-item :icon="SwitchButton" command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <div class="flex mr-10 justify-center items-center text-white">
        <el-icon class=" text-white" style="transform: rotate(90deg);">
          <MoreFilled />
        </el-icon>
      </div>
    </div>
  </div>
</template>
<style scoped>
.logo {
  float: left;
  width: 170px;
  height: 31px;
  margin: 8px 24px 8px 0;
  background: rgba(255, 255, 255, 0.3);
}

.el-dropdown-link {
  cursor: pointer;
  /* color: var(--el-color-primary); */
}
</style>