<template>

    <el-dialog v-model="dialogFormVisible" title="信息" width="500">
        <el-form :model="form">
            <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="userform.username" autocomplete="off" />
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="userform.email" placeholder="请输入邮箱" />
            </el-form-item>
            <el-form-item label="联系方式" :label-width="formLabelWidth">
                <el-input v-model="userform.phone" placeholder="请输入手机号" />
            </el-form-item>
            <!-- //插入图像 -->
            <!-- 学习经历 -->
            <div v-for="(exp, index) in userform.studyExperienceList" :key="index">
                <el-divider>学习经历 {{ index + 1 }}</el-divider>
                <el-form-item label="学校">
                    <el-input v-model="exp.schoolName" />
                </el-form-item>
                <el-form-item label="开始日期">
                    <el-date-picker v-model="exp.startDate" type="date" value-format="YYYY-MM-DD" />
                </el-form-item>
                <el-form-item label="结束日期">
                    <el-date-picker v-model="exp.endDate" type="date" value-format="YYYY-MM-DD" />
                </el-form-item>
                <el-button type="danger" @click="removeExperience(index)">删除</el-button>
            </div>

            <!-- 添加按钮 -->
            <el-button type="primary" @click="addExperience()">添加学习经历</el-button>

        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="cleanform(); dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit(); dialogFormVisible = false">
                    提交
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script lang="ts" setup>
import { adminAPI } from '@/api/adminAPI'
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'


const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
interface StudyExperience  {
   schoolName: string
  startDate: string
  endDate: string
}
interface UserForm {
  id: string
  username: string
  email: string
  phone: string
  studyExperienceList: StudyExperience[]
}
const form: UserForm = {
    id: '',
    username: '',
    email: '',
    phone: '',
    studyExperienceList: [],

}


defineExpose({ showform })//将方法暴露给父组件，由于父组件无法直接修改子组件中的变量

const userform = reactive<UserForm>({
  id: '',
  username: '',
  email: '',
  phone: '',
  studyExperienceList: []
})



//显示表单，根据传没传数据分别处理
function showform(row: any) {
    if (row) {
        Object.assign(userform, row);
    }
    dialogFormVisible.value = true;
}


// 调用后端接口写入新用户数据或更新用户信息
async function onSubmit() {
    if (userform.id) {
        await adminAPI.updateuser(userform);
        ElMessage.success('用户信息更新成功！');
    } else {
        await adminAPI.add(userform);
        ElMessage.success('用户添加成功！');
    }

    Object.assign(userform, form);
}


//清空填入的某些信息
function cleanform() {
    Object.assign(userform, form);
     userform.studyExperienceList = []
}

const addExperience = (): void => {
  userform.studyExperienceList.push({
    schoolName: '',
    startDate: '',
    endDate: ''
  })
}

const removeExperience = (index: number): void => {
  userform.studyExperienceList.splice(index, 1)
}
</script>
