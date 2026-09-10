package com.example.demo.vo;

import lombok.Data;

@Data 
public class LoginVo {
    private String token;      
    private UserVo user;
}
