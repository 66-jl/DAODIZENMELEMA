package com.example.demo.service.impl;

import com.example.demo.entity.OperationLog;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserlogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserlogServiceimpl implements UserlogService {
    @Resource
    UserMapper userMapper;
    @Override
    @Transactional(rollbackFor = Exception.class , propagation = Propagation.REQUIRES_NEW)
    public void savelog(OperationLog operationLog) {
        userMapper.savelog(operationLog);
    }
}
