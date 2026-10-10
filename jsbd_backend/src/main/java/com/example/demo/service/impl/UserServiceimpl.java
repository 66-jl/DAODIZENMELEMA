package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.OperationLog;
import com.example.demo.entity.StudyExperience;
import com.example.demo.service.UserlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.BussinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.ResponseCode;
import com.example.demo.service.UserService;
import com.example.demo.vo.PageVo;

import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

@Slf4j
@Service
public class UserServiceimpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserlogService userlogService;
    @Autowired
    private View error;


    @Override
    public User finduser(User user) {
        return userMapper.finduser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(UserDTO userDTO) throws Exception {
        User user = new User();
        try {

            BeanUtils.copyProperties(userDTO, user);
            if (userMapper.countByUsername(user.getUsername()) > 0) {
                throw new BussinessException(ResponseCode.USERNAME_EXCIT);
            }
            userMapper.Saveuser(user);
            List<StudyExperience> exprlist = userDTO.getStudyExperienceList();
            if (exprlist != null && !exprlist.isEmpty()) {
                //遍历集合为每个元素复制userid
                exprlist.forEach(expr -> {
                    expr.setUserId(user.getId());
                    expr.setCreateTime(LocalDateTime.now());
                    expr.setUpdateTime(LocalDateTime.now());

                });
                userMapper.insertStuExpr(exprlist);

            }
            OperationLog operationLog = new OperationLog(null, LocalDateTime.now(), "新增用户" + userDTO.getUsername());
            userlogService.savelog(operationLog);
        } catch (Exception e) {
            OperationLog operationLog = new OperationLog(null, LocalDateTime.now(), "新增用户失败");
            userlogService.savelog(operationLog);

            throw e;

        }
        return user;
    }

    @Override
    public PageVo<User> findbyPage(User user, Integer pageNum, Integer pageSize) {
        log.info("获取分页");
        Integer offset = (pageNum - 1) * pageSize;
        List<User> userdata = userMapper.findbyPage(user, offset, pageSize);
        Integer total = userMapper.countuser();
        PageVo<User> page = new PageVo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(userdata);
        page.setTotal(total);
        return page;
    }

    @Override
    public void updateuser(User user) {
        userMapper.updateuser(user);
    }

    @Override
    public void delUser(List<Long> ids) {
        userMapper.delUser(ids);
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public List<User> findall() {
        return userMapper.findall();
    }

}
