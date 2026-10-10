<template>
  <el-upload
    class="avatar-uploader"
    action="http://localhost:8001/he/upload"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    :headers="uploadHeaders"
    name="file"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
</template>

<script setup>
import { ref,watch } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

const props = defineProps({
  modelValue: { type: String, default: '' }
})
const emit = defineEmits(['update:modelValue'])
const imageUrl = ref(props.modelValue)

watch(() => props.modelValue, (val) => {
  imageUrl.value = val
})

// 如果需要 Token 鉴权，在此处添加
const uploadHeaders = ref({
  // Authorization: 'Bearer ' + localStorage.getItem('token')
})

const beforeAvatarUpload = (rawFile) => {
  console.log('文件类型 type:', rawFile.type)
  console.log('文件大小 size:', rawFile.size, '字节 =', (rawFile.size / 1024 / 1024).toFixed(2), 'MB')
  const isImage = rawFile.type.startsWith('image/')
  const isLt2M = rawFile.size / 1024 / 1024 < 2

  if (!isImage) {
    ElMessage.error('只能上传图片文件！')
    return false
  }
  if (!isLt2M) {
    ElMessage.error('图片大小不能超过 2MB！')
    return false
  }
  return true
}

// 关键：根据后端实际返回格式解析
const handleAvatarSuccess = (response) => {
  // 场景 A: 后端直接返回 url 字符串（最常见）
  if (typeof response === 'string' && response.startsWith('http')) {
    imageUrl.value = response
    ElMessage.success('头像上传成功')
    return
  }

  // 场景 B: 后端返回 JSON 包装类，如 { code: 0, data: { url: "..." } }
  if (response.code === 200 && response.data) {
    imageUrl.value = response.data
    emit('update:modelValue', response.data)  // 同步给父组件
    ElMessage.success('上传成功')
    return
  }

  // 场景 C: 后端返回 { url: "..." }
  if (response.url) {
    imageUrl.value = response.url
    ElMessage.success('上传成功')
    return
  }

  ElMessage.error('上传失败：' + JSON.stringify(response))
}
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>