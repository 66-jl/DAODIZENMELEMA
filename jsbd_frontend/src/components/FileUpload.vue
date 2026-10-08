<template>
    <div class="file-upload">
        <el-upload
            v-model:file-list="fileList"
            list-type="picture-card"
            :limit="limit"
            :accept="accept"
            :http-request="customUpload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :on-exceed="handleExceed"
            :before-upload="beforeUpload"
        >
            <el-icon><Plus /></el-icon>
        </el-upload>

        <!-- 图片预览弹窗 -->
        <el-dialog v-model="previewVisible" :append-to-body="true" title="预览">
            <img :src="previewUrl" style="width: 100%" alt="预览图" />
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import { post } from '@/utils/request';
import { getFileUrl } from '@/utils/utils';

// 父组件通过 v-model 传入已保存的文件相对路径（字符串）
const props = defineProps({
    modelValue: { type: String, default: '' },         // 已保存的文件相对路径
    action: { type: String, default: '/file/upload' }, // 上传接口路径
    limit: { type: Number, default: 1 },               // 最多上传数量
    accept: { type: String, default: 'image/*' },      // 允许的文件类型
    maxSize: { type: Number, default: 2 },             // 单个文件最大体积(MB)
});

const emit = defineEmits(['update:modelValue']);

const fileList = ref<any[]>([]);
const previewVisible = ref(false);
const previewUrl = ref('');

// 父组件传入的路径变化时，同步成 el-upload 能显示的 fileList
watch(
    () => props.modelValue,
    (url) => {
        fileList.value = url
            ? [{ name: url.split('/').pop() || url, url: getFileUrl(url) }]
            : [];
    },
    { immediate: true }
);

// 上传前校验体积（类型交给 accept 过滤）
function beforeUpload(file: File) {
    if (props.maxSize > 0 && file.size / 1024 / 1024 > props.maxSize) {
        ElMessage.error(`文件大小不能超过 ${props.maxSize}MB`);
        return false;
    }
    return true;
}

// 自定义上传：走项目里的 axios，自动带 token + baseURL
async function customUpload(options: any) {
    const formData = new FormData();
    formData.append('file', options.file);
    try {
        const res: any = await post(props.action, formData);
        options.onSuccess(res);   // 通知 el-upload 上传成功
    } catch (err) {
        options.onError(err);     // 通知 el-upload 上传失败
    }
}

// 上传成功回调：后端约定返回 { code:200, data:文件相对路径 }
function handleSuccess(response: any, uploadFile: any) {
    if (response?.code === 200) {
        uploadFile.url = getFileUrl(response.data);   // 让缩略图显示后端地址
        emit('update:modelValue', response.data);     // 把相对路径回传给父组件
    } else {
        ElMessage.error(response?.message || '上传失败');
    }
}

// 删除文件
function handleRemove() {
    emit('update:modelValue', '');
}

// 点击预览
function handlePreview(file: any) {
    previewUrl.value = file.url;
    previewVisible.value = true;
}

// 超出数量限制
function handleExceed() {
    ElMessage.warning(`最多只能上传 ${props.limit} 个文件`);
}
</script>

<style lang="less" scoped>
@import url('../styles/var.less');

.file-upload {
    :deep(.el-upload--picture-card),
    :deep(.el-upload-list--picture-card .el-upload-list__item) {
        width: @upload--item-width;
        height: @upload--item-height;
    }
}
</style>
