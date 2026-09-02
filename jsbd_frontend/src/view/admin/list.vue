<template>
    <div style="padding-top: 10px ;">
        <el-form :inline="true">
            <el-form-item label="姓名">
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
                <el-button type="info" @click="resetlist()">重置</el-button>
                <el-button type="info"  @click="conaddref()" >新增</el-button>
                <el-button type="danger" @click="deluser()">批量删除</el-button>

            </el-form-item>



        </el-form>

        
        <router-view></router-view>
        

        <el-divider boder-style="dashed" />

        <el-table border 
        :data="datalist"
         v-loading="listloading" 
         style="width: 100%"
         @selection-change="handleSelectionChange"
            :header-cell-style="{ background: '#f5f5f5' }">
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="username" label="姓名" style="width: 20%" />
            <el-table-column prop="email" label="邮箱" style="width: 20%" />
            <el-table-column prop="phone" label="联系方式" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary"  @click="updateuser(scope.row)">修改</el-button>
                    <el-button type="danger" @click="deluser(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>

        </el-table>

        <el-pagination @current-change="currentpagechange" background layout="prev, pager, next"
            style="float: right;margin: 10px 20px 10px 0px;" :total="listtotal" />
        <addform ref="addref"> </addform><!--ref="addref"：Vue 会把 addform 这个组件的实例（内存地址）存到父组件的一个变量里，这个变量名就叫 addref -->

    </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { adminAPI } from '@/api/adminAPI';
import Constants from '@/utils/constants';
import addform from './addform.vue';
import { ElMessage, ElMessageBox } from 'element-plus';

const datalist = ref([]);

const listloading = ref(false);
const listtotal = ref(0);
const addref = ref();
const multipleSelection = ref<any[]>([]);
const queryFormstate = {
    id:'',
    username: '',
    email: '',
    phone: '',
    pageNum: 1,
    pageSize: Constants.PAGE_SIZE,
}
const queryForm = reactive({ ...queryFormstate })
async function getlist() {
    try {
        listloading.value = true;
        let responeModel = await adminAPI.querylist(queryForm, queryForm.pageNum, queryForm.pageSize);
        datalist.value = responeModel.data.data;
        listtotal.value = responeModel.data.total;

    }
    catch (err) {
        console.log(err)

    }
    finally {
        listloading.value = false;

    }
}
function searchuser() {
    queryForm.pageNum = 1;
    getlist();
}


//重置功能按钮方法
function resetlist() {
    queryForm.username = '';//清空表单
    getlist();

}

//翻页更新表格
function currentpagechange(val: number) {
    queryForm.pageNum = val;
    getlist();

}
function conaddref(){
    addref.value.showform();

}

/**
 * 修改信息
 */

function updateuser(row:any){
    addref.value.showform(row);
    // console.log(row);
}

//删除方法
async function del(id?:any){
    try{
        listloading.value=true;
        let ids=id?[id]:multipleSelection.value.map(item=>item.id);
        await adminAPI.deluser(ids);

    }
    catch(err){

    }finally{
        listloading.value=false;
    }
}


//删除按钮
function deluser(id?:any){
    ElMessageBox.confirm(
    '确认要删除该条信息吗',
    '注意！',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
        del(id);
      ElMessage({
        type: 'success',
        message: '已删除',
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除取消',
      })
    })

}

//获取选中多行的数据
function handleSelectionChange(val:any) {
  multipleSelection.value = val;
}
onMounted(getlist);
</script>