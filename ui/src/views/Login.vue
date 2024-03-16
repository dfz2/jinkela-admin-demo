<script setup lang="ts">

import { onBeforeMount, onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from "vue-router";
import { INDEX } from "@/router/routes.js";
import { getPublicKeyApi, loginApi } from "@/api/user";
import JSEncrypt from 'jsencrypt/bin/jsencrypt';

const jsEncrypt = new JSEncrypt();
const formRef = ref(null)
const router = useRouter()
const route = useRoute()
const loading = ref<boolean>(false)

const state = reactive<API.LoginDTO>({
  username: 'admin',
  password: '123456',
})

onBeforeMount(async () => {
})

onMounted(async () => {
  // const res = await getPublicKeyApi({})
  // state.publicKey = res.publicKey
})

const handleLogin = async () => {
  await formRef.value?.validate()
  loading.value = true
  try {
    // const res = await getPublicKeyApi({})
    const res = await getPublicKeyApi()
    jsEncrypt.setPublicKey(res.publicKey);
    await loginApi({ username: state.username, password: jsEncrypt.encrypt(state.password) })
  } catch (err) {
    loading.value = false
    ElMessage({
      message: '用户名或密码错误',
      type: 'error',
    });
  }

  const {
    query: { redirect }
  } = route
  const path = typeof redirect === 'string' ? redirect : INDEX
  router.push(path)
};

</script>


<template>
  <div class="login-form flex justify-center items-center">
    <el-card shadow="never">
      <el-space direction="vertical" fill style="width: 100%;">
        <div class="mb-5 mt-5 flex justify-center">
          <h2>xxxx Admin demo</h2>
        </div>
        <el-form ref="formRef" :model="state" size="large">
          <el-form-item label="" prop="username">
            <el-input v-model="state.username" autocomplete="off" />
          </el-form-item>
          <el-form-item label="" prop="password">
            <el-input v-model="state.password" type="password" autocomplete="off" show-password />
          </el-form-item>
        </el-form>
        <el-button type="primary" size="large" :loading="loading" @click="handleLogin" auto-insert-space>登录</el-button>
      </el-space>
    </el-card>
  </div>
</template>

<style scoped>
.login-form {
  padding: 0;
  margin: 0;
  height: 100vh;
  width: 100vw;
  background-size: 300% 300%;
  background-image: linear-gradient(-45deg,
      rgba(59, 173, 227, 1) 0%,
      rgba(87, 111, 230, 1) 25%,
      rgba(152, 68, 183, 1) 51%,
      rgba(255, 53, 127, 1) 100%);
  animation: BGAnimate 15s ease infinite;
}

.el-card {
  width: 350px;
  height: 350px;
}


@keyframes BGAnimate {
  0% {
    background-position: 0% 50%
  }

  50% {
    background-position: 100% 50%
  }

  100% {
    background-position: 0% 50%
  }
}
</style>
