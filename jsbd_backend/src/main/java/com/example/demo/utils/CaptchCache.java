package com.example.demo.utils;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CaptchCache {
    //验证码缓存
    private static ConcurrentHashMap<String,String> Captchamap = new ConcurrentHashMap<>();


    //存储验证码
    public  void storecaptcha(String captchaId,String captcha) {
        Captchamap.put(captchaId,captcha);
        
    }
    
    public  void removecaptcha(String captchaId,String captcha) {
        Captchamap.remove(captchaId);
        
    }

    //验证验证码
    public  boolean  validatecaptcha(String captchaId,String captcha) {
        //获取存储的验证码
        String captchacode =  Captchamap.get(captchaId);
        if(captchacode==null){
            return false;
        }else{
            if(captchacode.equals(captcha)){
                Captchamap.remove(captchaId);
                return true;
            }
            else{
                return false;
            }
        }
        
    }
}
