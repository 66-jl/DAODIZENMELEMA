<template>
    <div class="login-container">
        <div class="login-box">
            <h2 class="login-title">地球管理系统</h2>
            <el-form ref="formref" :model="form" size="large" :rules="rules">
                <el-form-item prop="username">
                    <el-input
                        v-model="form.username"
                        placeholder="请输入用户名"
                        :prefix-icon="User"
                        clearable
                    />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        v-model="form.password"
                        type="password"
                        placeholder="请输入密码"
                        :prefix-icon="Lock"
                        show-password
                    />
                </el-form-item>
                <el-form-item prop="captchaCode">
                    <div>
                        <el-input
                        v-model="form.captchaCode"
                        placeholder="请输入验证码"
                        style="width:268px"
                    />
                    <img :src="captchaimage" @click="getcaptcha()"/>
                    </div>
                </el-form-item >
                <el-button type="primary" class="login-btn" :loading="btnloading" @click="onSubmit()">登 录</el-button>
            </el-form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive,ref,onMounted } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';
import { commonAPI } from '@/api/commmonAPI';
import { adminAPI } from '@/api/adminAPI';
import router from '@/router';
import { ElMessage } from 'element-plus';
import { useUserInfoStore } from '@/stores/user';
const captchaimage=ref('')

const formref = ref();
// v-model 一个绑定对象
const loginform = ({
    username: '',
    password: '',
    captchaId:'',
    captchaCode:'',
});
const form = reactive({ ...loginform });
const btnloading = ref(false);

//表单规则
const rules = reactive({
  username: [
    {
      required: true,
      message: '请输入用户名',
      trigger: 'blur'
    }
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur'
    }
  ],
  captchaCode: [
    {
      required: true,
      message: '请输入验证码',
      trigger: 'blur'
    }
  ]
})

//获取后端验证码方法
async function getcaptcha() {
    try{
      let captchResult =  await commonAPI.getcaptcha();
      captchaimage.value = captchResult.data.captchaImage;
      form.captchaId = captchResult.data.captchaId;

    }catch(e){

    }finally{

    }
    
}

function onSubmit(){
    formref.value.validate().then(async () => {
  try {
    btnloading.value = true;
    let result = await adminAPI.login(form);
    console.log(result.data);

    useUserInfoStore().setuserInfo(result.data);

    //跳转首页
    router.push('/index');
    ElMessage({
        type: 'success',
        message: '登陆成功',
      });

  } catch (error:any) {
     if (error.response?.data?.code !== 200) {
        getcaptcha();
    }

  } finally {
    btnloading.value = false;
  }
})
}

onMounted(getcaptcha);

</script>

<style lang="less">
@import url('../styles/login.less');
</style>
