package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.response.Result;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Void> handlerException(Exception e){
        return Result.error(e.getMessage());
        
    }


    @ExceptionHandler(BussinessException.class)
    public Result<Void> handlerBussinessException(BussinessException e){
        return Result.error(e.getCode(),e.getMessage());

    }

}
