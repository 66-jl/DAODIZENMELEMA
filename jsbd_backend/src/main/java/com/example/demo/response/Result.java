package com.example.demo.response;

import lombok.Getter;

@Getter
public class Result<T> { // result<T>,Result 对象，且这个对象内部的 data 类型是 T。

    private Integer code;
    private String message;

    private  T data;

    // private Result(Integer code){
    //     this.code=code;
    // }

    private Result(Integer code, String message,T data){
        this.code = code;
        this.message = message;
        this.data=data;
    }

    public Result(ResponseCode responseCode,T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
  
    }


    public static <T> Result<T> success() {
        return new Result<>(ResponseCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCode.SUCCESS, data);
    }

    public static <T> Result<T> error(Integer code,String message) {
        return new Result<>(code, message,null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ResponseCode.ERROE.getCode(),message, null);
    }



}
