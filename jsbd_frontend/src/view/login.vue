<template>
    <div class="login-container">
        <div class="login-box">
            <h2 class="login-title">地球管理系统</h2>
            <el-form :model="form" size="large">
                <el-form-item>
                    <el-input
                        v-model="form.username"
                        placeholder="请输入用户名"
                        :prefix-icon="User"
                        clearable
                    />
                </el-form-item>
                <el-form-item>
                    <el-input
                        v-model="form.password"
                        type="password"
                        placeholder="请输入密码"
                        :prefix-icon="Lock"
                        show-password
                    />
                </el-form-item>
                <el-form-item>
                    <div>
                        <el-input
                        v-model="form.captchacode"
                        placeholder="请输入验证码"
                        style="width:268px"
                    />
                    <img :src="captchaimage" @click="getcaptcha()"/>
                    </div>
                </el-form-item prop="captchacode">
                <el-button type="primary" class="login-btn">登 录</el-button>
            </el-form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive,ref,onMounted } from 'vue';
import { User, Lock } from '@element-plus/icons-vue';
import { commonAPI } from '@/api/commmonAPI';
const captchaimage=ref('')

// 只是给 v-model 一个绑定对象，后续验证、登录逻辑你自己往里加
const form = reactive({
    username: '',
    password: '',
    captchaId:'',
    captchacode:'',
});

//获取后端验证码方法
async function getcaptcha() {
    try{
      let captchResult =  await commonAPI.getcaptcha();
      captchaimage.value = captchResult.data.captchaImage;
      form.captchaId = captchResult.data.captchaId;

    }catch{

    }finally{

    }
    
}
onMounted(getcaptcha);

</script>

<style lang="less">
@import url('../styles/login.less');
</style>
