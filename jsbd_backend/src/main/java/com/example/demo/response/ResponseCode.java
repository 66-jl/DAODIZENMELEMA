package com.example.demo.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200,"操作成功"),
    USERNAME_EXCIT(2001,"用户已存在"),
    ERROE(500,"操作失败");

    private  Integer code;
    private  String message;

}
