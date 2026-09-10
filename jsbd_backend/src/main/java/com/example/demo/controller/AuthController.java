package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginDTO;
import com.example.demo.response.Result;
import com.example.demo.service.AuthService;
import com.example.demo.vo.LoginVo;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/he/Auth")
public class AuthController {
    
    @Resource 
    AuthService authService;
    @Autowired
    private UserService userService;

    //登录接口
    @PostMapping("/login")
    @CrossOrigin 
    public Result<LoginVo> loginMethod(@RequestBody LoginDTO loginDTO) {
        LoginVo loginVo = authService.checkpsd(loginDTO);

        return Result.success(loginVo);
    }


    @GetMapping("/logout")
    @CrossOrigin
    public Result<Void> logoutMethod() {
        userService.logout();

        return Result.success();
    }
    
}
