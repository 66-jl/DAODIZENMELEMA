package com.example.demo.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class kaptchaconfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();

        // ========== 基础样式 ==========
        properties.setProperty("kaptcha.border", "no");                           // 无边框
        properties.setProperty("kaptcha.border.color", "34,114,200");             // 边框色（不生效但保留）
        properties.setProperty("kaptcha.image.width", "125");                    // 图片宽度
        properties.setProperty("kaptcha.image.height", "40");                   // 图片高度
        properties.setProperty("kaptcha.background.clear.from", "white");        // 背景渐变起始色（白色）
        properties.setProperty("kaptcha.background.clear.to", "white");          // 背景渐变结束色（白色）

        // ========== 验证码内容 ==========
        properties.setProperty("kaptcha.textproducer.char.string", "123456789"); // 字符集（纯数字，不含0避免混淆）
        properties.setProperty("kaptcha.textproducer.char.length", "4");         // 验证码长度（4位）
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Arial Narrow,Serif"); // 字体
        properties.setProperty("kaptcha.textproducer.font.size", "38");          // 字号

        // ========== 干扰器 ==========
        // 方案一：无干扰（最清晰，适合纯数字验证码）
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        // 方案二：如果你想要轻微干扰（防止机器识别），可以替换为水纹干扰：
        // properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        // properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        // ========== 可选：设置验证码的字符颜色 ==========
        // properties.setProperty("kaptcha.textproducer.font.color", "black");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
