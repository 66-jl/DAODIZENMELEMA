package com.example.demo.vo;

import java.util.List;

import lombok.Data;

@Data
public class PageVo<T> {
    private Integer pageNum;
    private  Integer pageSize;
    private  Integer total;
    private  List<T> data;
    
}
