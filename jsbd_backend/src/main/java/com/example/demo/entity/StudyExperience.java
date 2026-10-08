package com.example.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学习经历表
 */
@Data
public class StudyExperience {

    /**
     * 主键
     */
    private Long id;

    /**
     * 关联 user.id
     */
    private Integer userId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 逻辑删除：0未删除，1已删除
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}