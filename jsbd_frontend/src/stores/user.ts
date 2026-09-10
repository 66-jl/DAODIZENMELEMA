import constants from "@/utils/constants";
import { clearLocalStorage, getLocalStorage, saveLocalStorage } from "@/utils/utils";
import { defineStore } from "pinia";
import { computed, reactive, ref } from "vue";
export const useUserInfoStore = defineStore('userInfo', () => {
  const token = ref('')
  const user = reactive({
    id: '',
    username: '',
    email: '',
    phone: ''
  })
  const getToken = computed(() => {
    if (token.value) {
      return token.value;
    }
    return getLocalStorage(constants.USERTOKEN);

  })
  function setuserInfo(data: any) {
    user.id = data.user.id;
    user.username = data.user.username;
    user.email = data.user.email;
    user.phone = data.user.phone;
    token.value = data.token;
    saveLocalStorage(constants.USERTOKEN,token.value);
  }
  function logout(){
    user.id = '';
    user.username = '';
    user.email = '';
    user.phone = '';
    token.value = '';
    clearLocalStorage();

  }

  return { token, setuserInfo,getToken,user,logout }
})