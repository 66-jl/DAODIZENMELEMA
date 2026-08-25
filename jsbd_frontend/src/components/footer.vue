<template>
  <div class="version">
    <a target="_blank"  href="https://github.com/66-jl/DAODIZENMELEMA">©2025-{{ currentYear }}github仓库</a>
    <a target="_blank"  href="https://github.com/66-jl">git个人账号</a>
  </div>
</template>

<script setup>
import dayjs from 'dayjs';
const currentYear = dayjs().year();
</script>

<style lang="less" scoped>
.version {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.45);

  a {
    color: inherit;
    text-decoration: none;
    transition: color 0.2s;
    &:hover {
      color: #0a0e14;
    }
  }
}
</style>