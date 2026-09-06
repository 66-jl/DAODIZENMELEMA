package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BussinessException;
import com.example.demo.response.ResponseCode;
import com.example.demo.response.Result;
import com.example.demo.utils.CaptchCache;
import com.example.demo.vo.CaptchaVo;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;


@RestController
@RequestMapping("/he/common")
public class ConmmonController {
    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private CaptchCache captchCache;

    @CrossOrigin
    @GetMapping("/captcha")
    public Result<CaptchaVo> getMethodName() {
        String captchText = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(captchText);
        String base64Code = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
            base64Code = Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (IOException ex) {
            throw new BussinessException(ResponseCode.CAPTCHA_CREATE_ERROE);
        }
        CaptchaVo captcha = new CaptchaVo();
        captcha.setCaptchaImage("data:image/jpg;base64,"+base64Code);
        String captchid = UUID.randomUUID().toString();
        captcha.setCaptchaId(captchid);
        captchCache.storecaptcha(captchid, captchText);


        return Result.success(captcha);
    }


    @GetMapping("/doLogin")
	public String doLogin(String username, String password) {
		// 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
		if("zhang".equals(username) && "123456".equals(password)) {
			StpUtil.login(10001);
			return "登录成功";
		}
		return "登录失败";
	}

	// 查询登录状态，浏览器访问： http://localhost:8081/he/common/isLogin
	@PostMapping("/isLogin")
	public String isLogin() {
		return "当前会话是否登录：" + StpUtil.isLogin();
	}





}
