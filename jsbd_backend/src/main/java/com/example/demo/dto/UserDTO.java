package com.example.demo.dto;


import com.example.demo.entity.StudyExperience;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String avatarUrl;
    private String email;
    private String phone;

    private List<StudyExperience> studyExperienceList;



}
