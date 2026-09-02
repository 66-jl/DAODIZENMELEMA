package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.BussinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.ResponseCode;
import com.example.demo.service.UserService;
import com.example.demo.vo.PageVo;

import jakarta.annotation.Resource;

@Service
public class UserServiceimpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User finduser(User user) {
        return userMapper.finduser(user);
    }

    @Override
    public User addUser(User user) {
        // ===== 校验逻辑放这里 =====
        // if (user.getUsername() == null || user.getUsername().isBlank()) {
        //     throw new IllegalArgumentException("用户名不能为空");
        // }
        // if (user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        //     throw new IllegalArgumentException("邮箱格式不正确");
        // }
        // if (user.getPhone() == null || !user.getPhone().matches("^1[3-9]\\d{9}$")) {
        //     throw new IllegalArgumentException("手机号格式不正确");
        // }
        // 业务校验：用户名是否已存在（需要在 Mapper 加查询）
        if (userMapper.countByUsername(user.getUsername()) > 0) {
            throw new BussinessException(ResponseCode.USERNAME_EXCIT);
        }
        userMapper.Saveuser(user);
        return user;
    }

    @Override
    public PageVo<User> findbyPage(User user,Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        List<User> userdata = userMapper.findbyPage(user,offset, pageSize);
        Integer total = userMapper.countuser();
        PageVo<User> page = new PageVo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(userdata);
        page.setTotal(total);
        return page;
    }

    @Override
    public void updateuser(User user){
        userMapper.updateuser(user);
    }

    @Override
    public void delUser(List<Long> ids){
        userMapper.delUser(ids);
    }

}
