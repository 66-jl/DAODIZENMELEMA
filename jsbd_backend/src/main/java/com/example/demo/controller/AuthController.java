package com.example.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
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


    @PostMapping("kickout")
    @CrossOrigin
    public Result<Void> kickout(@RequestParam Long id) {

        // 踢人下线不会清除Token信息，而是将其打上特定标记，再次访问会提示：Token已被踢下线。
        StpUtil.kickout(id);

        // 返回
        return Result.success();
    }
}
