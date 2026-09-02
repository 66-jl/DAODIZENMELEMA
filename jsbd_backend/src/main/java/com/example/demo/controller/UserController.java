package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/he")
public class UserController {
    @Resource
    UserService userService;
    //
    @PostMapping("/user/find")
    public User getuser(@RequestBody User user) {
        return userService.finduser(user);
    }

    @PostMapping("/user/update")
    @CrossOrigin
    public Result<User> postMethodName(@RequestBody User user){
        userService.updateuser(user);
        return Result.success(user);
    }
    

    @PostMapping("/admin/add")
    @CrossOrigin
    public Result<User> adduser(@RequestBody User user){
        userService.addUser(user);
        return Result.success(user);
    }

    @PostMapping("/admin/list")
    @CrossOrigin
    public Result<PageVo<User>> findbyPageVo(@RequestBody User user,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize ) {
        PageVo<User> page =  userService.findbyPage(user,pageNum, pageSize);
         return Result.success(page);
        
    }

    @PostMapping("/user/del")
    @CrossOrigin
    public Result<Void> deluser(@RequestBody List<Long> ids) {
        userService.delUser(ids);
        
        return Result.success();
    }
    
    
}
