package com.example.demo.controller;


import cn.hutool.core.io.IoUtil;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
     UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/request")
    public String request(HttpServletRequest request1){//httpservletRequest类型获得各种请求参数

        System.out.println(request1.getMethod());

        return  "OK";
    }


    @RequestMapping("/response")
    public ResponseEntity<String> response(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("name","hehhh")
                .body("<h1>OK</h1>")
                ;
    }

    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {
        //加载并读取user。test，
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());

        //解析结合，封装为user对象
        List<User> userList = lines.stream().map(line ->{
           String[] part = line.split(",");
           Integer id = Integer.parseInt(part[0]);
           String username = part[1];
           String password = part[2];
            String email = part[3];
            String phone = part[4];
            return new User(id,username,password,email,phone);

        }).toList();

        return userList;
    }

    @GetMapping("/findall")
    public List<User> findall() {
       return userService.findall();
    }


}
