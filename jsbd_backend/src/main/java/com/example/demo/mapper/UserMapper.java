package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

public interface UserMapper {
    @Select("select * from user ORDER BY id ASC")
    List<User> findall();

    @Insert("insert into `user` (`username`,`email`,`phone`) VALUES (#{username},#{email},#{phone})")
    @Transactional
    void Saveuser(User user);

    @Select("select * from user limit #{offset},#{pageSize}")
    List<User> findbyPage(Integer offset,Integer pageSize);
    
    @Select("select count(id) from user")
    Integer countuser();

    @Select("select count(username) from user")
    int countByUsername(String username);

}
