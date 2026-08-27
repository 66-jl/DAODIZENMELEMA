<template>
    <div style="padding-top: 10px ;">
        <el-form :inline="true">
            <el-form-item  label="姓名">
                <el-input v-model="queryForm.username" placeholder="请输入姓名" />
            </el-form-item>
            <!-- <el-form-item label="性别" >
                <el-select v-model="form.region" placeholder="please select your zone" >
                    <el-option label="男" value="boy" />
                    <el-option label="女" value="girl" />
                </el-select>
            </el-form-item> -->

            <el-form-item>
                <el-button type="primary" @click="searchuser()">查询</el-button>
                <el-button @click="adduser()">新增</el-button>
                <el-button type="danger">批量删除</el-button>

            </el-form-item>

        </el-form>

        <el-divider boder-style="dashed" />

        <el-table border :data="datalist" v-loading="listloading" style="width: 100%"
            :header-cell-style="{ background: '#f5f5f5' }">
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="username" label="姓名" style="width: 20%" />
            <el-table-column prop="email" label="邮箱" style="width: 20%" />
            <el-table-column prop="phone" label="联系方式" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary">修改</el-button>
                    <el-button type="danger">删除</el-button>
                </template>
            </el-table-column>

        </el-table>

        <el-pagination background layout="prev, pager, next" style="float: right;margin: 10px 20px 10px 0px;"
            :total="listtotal" />

    </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { adminAPI } from '@/api/adminAPI';
import Constants from '@/utils/constants';

const datalist = ref([]);

const listloading = ref(false);
const listtotal = ref(0);
const queryFormstate={
    username:'',
    email:'',
    phone:'',
    pageNum:1,
    pageSize:Constants.PAGE_SIZE,
}
const queryForm = reactive({...queryFormstate})
async function getlist() {
    try {
        listloading.value = true;
       let responeModel = await adminAPI.querylist(queryForm,queryForm.pageNum,queryForm.pageSize);
       datalist.value=responeModel.data.data;
       listtotal.value=responeModel.data.total;
       console.log(responeModel);
       
    }
    catch (err) {
        console.log(err)
        
    }
    finally {
        listloading.value=false;

    }
}
function searchuser(){
    queryForm.pageNum=1;
    getlist();
    queryForm.username='';
}
async function adduser(){
    await adminAPI.add(queryForm);
    queryForm.username='';
    getlist();

}
onMounted(getlist);
</script>