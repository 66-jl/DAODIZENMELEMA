package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.BussinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.ResponseCode;
import com.example.demo.service.AuthService;
import com.example.demo.utils.CaptchCache;
import com.example.demo.vo.LoginVo;
import com.example.demo.vo.UserVo;

import cn.dev33.satoken.stp.StpUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;

@Service
public class AuthServiceimpl implements AuthService {

    @Resource
    CaptchCache captchCache;
    @Resource
    UserMapper userMapper;

    @Override
    public LoginVo checkpsd(LoginDTO loginDTO) {

        /*
        * 1.验证码比对
        * 2.用户名密码比对
        * 3.satoken登录
         */
        if (StringUtils.isBlank(loginDTO.getCaptchaId()) || StringUtils.isBlank(loginDTO.getCaptchaCode())) {
            throw new BussinessException(ResponseCode.CAPTCHA_ERROR);
        }

        boolean flag = captchCache.validatecaptcha(loginDTO.getCaptchaId(), loginDTO.getCaptchaCode());
        if (!flag) {
            throw new BussinessException(ResponseCode.CAPTCHA_ERROR);
        }

        User user = userMapper.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        //查询失败抛出错误
        if (user == null) {
            throw new BussinessException(ResponseCode.USER_PSD_ERROR);
        }

        StpUtil.login(user.getId());

        //组装返回数据
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(StpUtil.getTokenValue());

        UserVo userInfo = new UserVo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhone(user.getPhone());
        loginVo.setUser(userInfo);
        return loginVo;

    }
}
