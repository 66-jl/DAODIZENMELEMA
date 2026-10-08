<template>
  <div class="box" @mousemove="handleMouseMove" ref="boxRef">
    <h1 class="title">欢迎光临</h1>
  </div>
</template>

<script setup lang="js">
import { ref } from 'vue'

const boxRef = ref(null)

function handleMouseMove(e) {
  const box = boxRef.value
  const rect = box.getBoundingClientRect()
  // 计算鼠标在区域内的相对位置（百分比）
  const x = ((e.clientX - rect.left) / rect.width) * 100
  const y = ((e.clientY - rect.top) / rect.height) * 100
  // 设置 CSS 变量，让背景跟随鼠标
  box.style.setProperty('--mouse-x', `${x}%`)
  box.style.setProperty('--mouse-y', `${y}%`)
}
</script>

<style scoped>
.box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 750px;
  background: #fff;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

/* 用伪元素做跟随鼠标的光圈 */
.box::before {
  content: '';
  position: absolute;
  inset: 0;
  background: radial-gradient(
    circle 120px at var(--mouse-x, 50%) var(--mouse-y, 50%),
    rgba(247, 36, 36, 0.8),
    transparent 70%
  );
  opacity: 0;
  transition: opacity 0.4s ease;
  pointer-events: none;  /* 让鼠标事件穿透到 box 上 */
}

.box:hover::before {
  opacity: 1;
}

.title {
  font-size: 8rem;
  font-weight: 900;
  color: #fff;
  position: relative;
  z-index: 1;
}
</style>