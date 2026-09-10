// 1. 创建 axios 实例（别直接用 axios.get）
import axios from 'axios';
import type { AxiosRequestConfig, AxiosResponse } from 'axios';
import Constants from './constants';
import {saveLocalStorage,getLocalStorage,clearLocalStorage} from './utils';
import {ElMessage,ElMessageBox} from 'element-plus';


const TOKEN_NAME='satoken';

// 创建 axios 实例
const service = axios.create({
  baseURL: Constants.BASE_URL, // 统一从环境变量读取
  timeout: 15000
});

function logout() {
  clearLocalStorage();
  location.href = '/'; // 跳转到登录页
}

// 2. 请求拦截器（自动加 token）
service.interceptors.request.use(config => {
  const token = getLocalStorage(Constants.USERTOKEN);
  if (token) {
    config.headers[TOKEN_NAME] = token;
  }else{
    delete config.headers[TOKEN_NAME]
  }
  return config;
}, (error:any) => {
  return Promise.reject(error);
}
);

// 3. 响应拦截器（统一处理错误码、401跳转登录）
// service.interceptors.response.use(
//   response => response.data,
//   error => {
//     if (error.response?.status === 401) {
//       // 跳转登录页
//       clearLocalStorage();
//       ElMessageBox.alert('登录已过期，请重新登录', '提示', {
//         confirmButtonText: '确定',
//         callback: () => {
//           logout();
//         }
//       });
//     }
//     return Promise.reject(error);
//   }
// );
service.interceptors.response.use(
  response => {
    const res = response.data;
    // 后端返回 200 但业务码不对的情况（如果以后去掉 @ResponseStatus 会走这里）
    if (res && res.code !== undefined && res.code !== 200) {
      ElMessage.error(res.message || '请求失败');
      return Promise.reject(res);
    }
    if (res.code === 11012 || res.code === 11011) {
      ElMessage.closeAll();
      ElMessage.error('您没有登录，请重新登录');
      setTimeout(logout, 300);
      return Promise.reject(response);
    }
    return res;
  },
  error => {
    if (error.response?.status === 401) {
      clearLocalStorage();
      ElMessageBox.alert('登录已过期，请重新登录', '提示', {
        confirmButtonText: '确定',
        callback: () => logout(),
      });
    } else {
      ElMessage.error(error.response?.data?.message || '请求失败');  // ← 补上这行
    }
    return Promise.reject(error);
  }
);
//通用请求封装

export const http = (config: AxiosRequestConfig<any>) => {
  return service.request<any>(config);
}
/**
 * 
 * get请求
 */
export const get = (url: string, params?: any) => {
  return http({
    url,
    method: 'get',
    params
  });
}
/**
 * post请求
 */
export const post = (url: string, data?: any) => {
  return http({
    url,
    method: 'post',
    data
  });
} 