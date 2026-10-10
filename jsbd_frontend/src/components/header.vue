<template>
    <div class="header">
        <div class="header-menu">
            <div class="header-logo">
                <div class="header-title">地球管理系统</div>
            </div>
            <div class="header-rinfo">
                <img v-if="user.avatarUrl" :src="user.avatarUrl" class="header-avatar" alt="头像" />
                当前用户：
                <span> {{ user.username }} </span>
                <span class="header-exit">
                    <a @click="logout">退出</a>
                </span>
            </div>
        </div>


    </div>
</template>

<script lang="ts" setup>
import { adminAPI } from '@/api/adminAPI';
import { useUserInfoStore } from '@/stores/user';
import { storeToRefs } from 'pinia';


const userInfoStore = useUserInfoStore()           
const { user } = storeToRefs(userInfoStore)

async function logout(){
    try{
        await adminAPI.logout();
        window.location.href = '/'
    }
    catch(e){
        console.log(e);

    }finally{
        useUserInfoStore().logout();
    }
}


</script>

<style scoped>
.header-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  object-fit: cover;
  vertical-align: middle;
  margin: 0 6px;
  border: 1px solid #eee;
}
</style>
