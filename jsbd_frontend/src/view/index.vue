// 后台主页面
<template>
    <div class="common.layout">
        <el-container>
            <el-header style="height: 90px">
                <Header></Header>
            </el-header>
            <el-container>
                <el-aside class="sidebar sidebar--dark">
                    <Leftmenu></Leftmenu>
                </el-aside>
                <el-container>
                    <div class="content_wrapper">
                        <el-main class="content">
                            <el-card :body-style="sitecontent.sitecontentHeight">
                                <router-view></router-view>
                            </el-card>
                        </el-main>
                        <el-footer class="layout-footer"><Footer></Footer></el-footer>
                    </div>

                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup lang="ts">
import Header from '../components/header.vue';
import Leftmenu from '../components/leftmenu.vue';
import Footer from '../components/footer.vue';
import { ref,reactive } from 'vue';
/**
 * 定义内容区域常量
 */
const sitecontent =  reactive(
    {
        documentClientHeight:0,
        sitecontentHeight:{}
    }
)
/**
 * 获取内容高度
 */
function getsitecontent(){
    sitecontent.documentClientHeight = document.documentElement.clientHeight
    sitecontent.sitecontentHeight={
        minHeight:sitecontent.documentClientHeight - 90 - 100 + 'px' 
    }
}
getsitecontent();

window.onresize = function(){
    getsitecontent();
}
</script>
<style lang="less">
@import url('../styles/style.less');
</style>