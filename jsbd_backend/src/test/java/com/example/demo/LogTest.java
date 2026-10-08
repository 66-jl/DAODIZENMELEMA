package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testlog() {

        logger.debug("开始计算:  ");
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        logger.info("计算结果为："+sum);
        logger.debug("结束计算:  ");
    }
}
