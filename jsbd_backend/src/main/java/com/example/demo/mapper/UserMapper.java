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

   @Select("<script>" +
            "SELECT * FROM user" +
            "<where>" +
            "   <if test='user.username != null and user.username != \"\"'>" +
            "       AND username LIKE CONCAT('%', #{user.username}, '%')" +
            "   </if>" +
            "   <if test='user.email != null and user.email != \"\"'>" +
            "       AND email = #{user.email}" +
            "   </if>" +
            "   <if test='user.phone != null and user.phone != \"\"'>" +
            "       AND phone = #{user.phone}" +
            "   </if>" +
            "</where>" +
            " ORDER BY id DESC" +  
            " LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<User> findbyPage(User user,Integer offset,Integer pageSize);
    
    @Select("select count(id) from user")
    Integer countuser();

    @Select("select count(username) from user where username = #{username}")
    int countByUsername(String username);

}
