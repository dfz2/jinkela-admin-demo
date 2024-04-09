<template>
  <div>
    <el-card shadow="never">


      <el-button type="primary" :icon="Download" :loading="downloading" @click.stop="downloadFn">{{buttonText}}</el-button>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { useDownloadFn } from '@/hooks/useDownloadFn';
import { Download } from '@element-plus/icons-vue';
import { computed } from 'vue';

const { downloading, downloadFn } = useDownloadFn({})
const buttonText = computed(() => downloading.value ? '努力下载中...' : '测试下载文件按钮阿阿阿') 


</script>

<style scoped></style>