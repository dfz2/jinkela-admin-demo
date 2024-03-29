<script lang="ts" setup>
import AppHeader from './Header.vue';
import AppMenu from './Menu.vue';
import AppMain from './Main.vue';

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <AppHeader />
      </el-header>
      <el-container>
        <el-aside>
          <el-scrollbar>
            <AppMenu />
          </el-scrollbar>
        </el-aside>
        <el-main>
          <el-scrollbar>
            <AppMain />
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<style lang="scss" scoped>
.common-layout {
  --aside-width: 200px;
  --header-height: 48px;

  .el-header {
    height: var(--header-height);
    line-height: var(--header-height);
    background: linear-gradient(90deg, rgb(15 31 74), rgb(18 71 139), rgb(27 117 201));
  }

  .el-aside {
    width: var(--aside-width);
    height: calc(100vh - var(--header-height));
    color: var(--el-text-color-primary);
    background: #fff;
  }

  .el-menu {
    border-right: none;
  }

  .el-main {
    padding: 0;
    height: calc(100vh - var(--header-height));
  }

}


:deep(.el-menu-item.is-active) {
  background: var(--el-color-primary-light-9);
  border-right: 2px solid var(--el-color-primary);
}



</style>