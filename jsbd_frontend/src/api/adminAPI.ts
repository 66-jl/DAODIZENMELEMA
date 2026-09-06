import {get,post} from '@/utils/request';
export const adminAPI = {
    add: (params:any) => {
        return post('/user/add',params);
    },
    querylist: (params:any,pageNum:any,pageSize:any) => {
        return post('/user/list'+'?pageNum=' + pageNum + '&pageSize=' + pageSize,params);
    },

    finduser: (params:any)=>{
        return post('/user/find',params);
    },
    updateuser: (params:any)=>{
        return post('/user/update',params);
    },
    deluser:(params:any)=>{
        return post('/user/del',params);

    },
}//定义