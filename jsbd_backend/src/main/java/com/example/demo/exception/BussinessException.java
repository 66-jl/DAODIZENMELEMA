package com.example.demo.exception;

import com.example.demo.response.ResponseCode;

import lombok.Data;


@Data
public class BussinessException extends RuntimeException{

    private Integer code;//错误码   
    private String message;//异常信息

    // private Result(Integer code){
    //     this.code=code;
    // }


    public  BussinessException(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }


}
