package com.example.demo.controller;

import com.example.demo.response.Result;
import com.example.demo.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/he/upload")
public class UploadController {


    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping
    @CrossOrigin
    public Result<String> upload(MultipartFile file) throws Exception {

        log.info("文件上传,{}",file.getOriginalFilename());

        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename() );
        log.info("文件上传url,{}",url);

        return Result.success(url);
    }

}
