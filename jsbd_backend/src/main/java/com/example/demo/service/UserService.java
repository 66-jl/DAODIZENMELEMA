package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.vo.PageVo;

public interface UserService {
    User finduser(User user);
    User addUser(UserDTO userDTO) throws Exception;
    PageVo<User> findbyPage(User user,Integer pageNum, Integer pageSize);
    void delUser(List<Long> ids);

    public void updateuser(User user);

    void logout();
    List<User> findall();
}
