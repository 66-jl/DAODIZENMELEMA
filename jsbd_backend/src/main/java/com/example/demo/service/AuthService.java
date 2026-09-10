package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.vo.LoginVo;

public interface AuthService {
     LoginVo checkpsd(LoginDTO loginDTO);

}

