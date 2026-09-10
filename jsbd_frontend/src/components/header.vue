<template>
    <div class="header">
        <div class="header-menu">
            <div class="header-logo">
                <div class="header-title">地球管理系统</div>
            </div>
            <div class="header-rinfo">
                当前用户：
                <span> {{ userStore.user.username }} </span>
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



const userStore = useUserInfoStore();

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
