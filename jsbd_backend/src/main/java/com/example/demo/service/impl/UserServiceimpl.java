package com.example.demo.service.impl;

import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
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

    @Override
    public void logout() {
        StpUtil.logout();
    }

}
