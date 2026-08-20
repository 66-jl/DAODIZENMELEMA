package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.response.Result;
import com.example.demo.service.UserService;
import com.example.demo.vo.PageVo;

import jakarta.annotation.Resource;




@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping
    public List<User> getuser() {
        return userService.findall();
    }

    @PostMapping
    public Result<User> adduser(@RequestBody User user){
        userService.addUser(user);
        return Result.success(user);
    }

    @GetMapping("/page")
    public Result<PageVo<User>> findbyPageVo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize ) {
        PageVo<User> page =  userService.findbyPage(pageNum, pageSize);
         return Result.success(page);
        
    }
    
}
