package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.vo.PageVo;

public interface UserService {
    List<User> findall();
    User addUser(User user);
    PageVo<User> findbyPage(Integer pageNum, Integer pageSize);
}
