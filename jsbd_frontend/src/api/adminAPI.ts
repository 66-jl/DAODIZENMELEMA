import {get,post} from '@/utils/request';
export const adminAPI = {
    add: (params:any) => {
        return post('/admin/add',params);
    },
    // update: (params:any) => {
    //     return post('/update',params);
    // }
    querylist: (params:any,pageNum:any,pageSize:any) => {
        return post('/admin/list'+'?pageNum=' + pageNum + '&pageSize=' + pageSize,params);
    }
}//定义