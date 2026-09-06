import {get,post} from '@/utils/request';
export const commonAPI = {
    getcaptcha: () => {
        return get('/common/captcha');
    },

}//定义