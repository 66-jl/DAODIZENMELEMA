<template>
  <el-row class="tac">
    <el-col :span="12">
      <!-- <h5 class="mb-2">Default colors</h5> -->
      <el-menu default-active="2" class="sidebar" @open="handleOpen" @close="handleClose">
        <el-menu-item index="1-1" @click="$router.push({ path: '/admin' })">
          <el-icon>
            <person />
          </el-icon>
          管理员列表
        </el-menu-item>
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <location />
            </el-icon>
            <span>大洲管理</span>
          </template>
          <el-menu-item index="1-1" @click="$router.push({ path: '/Welcome' })">
            <el-icon>
              <location />
            </el-icon>
            亚洲
          </el-menu-item>
          <el-menu-item index="1-2"  >
            <el-icon>
              <location />
            </el-icon>
            非洲
          </el-menu-item>
          <el-menu-item index="1-3">
            <el-icon>
              <location />
            </el-icon>
            欧洲
          </el-menu-item>
          <el-sub-menu index="1-4">
            <template #title>美洲</template>
            <el-menu-item index="1-4-1">北美洲</el-menu-item>
            <el-menu-item index="1-4-2">南美洲</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><icon-menu /></el-icon>
            <span>海洋管理</span>
          </template>
          <el-menu-item index="2-1">北球海</el-menu-item>
          <el-menu-item index="2-2">南球海</el-menu-item>
        </el-sub-menu>
        <!-- <el-menu-item index="3" disabled>
          <el-icon><document /></el-icon>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon><setting /></el-icon>
          <span>Navigator Four</span>
        </el-menu-item> -->
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
// import Welcome from '@/view/welcome.vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
// import { permission } from 'process'
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>