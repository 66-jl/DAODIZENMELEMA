package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

public interface UserMapper {

    @Select("select * from user where id = #{id} ORDER BY id ASC")
    User finduser(User user);

    @Insert("insert into `user` (`username`,`email`,`phone`) VALUES (#{username},#{email},#{phone})")
    @Transactional
    void Saveuser(User user);

    @Select("<script>"
            + "SELECT * FROM user"
            + "<where>"
            + "   <if test='user.username != null and user.username != \"\"'>"
            + "       AND username LIKE CONCAT('%', #{user.username}, '%')"
            + "   </if>"
            + "   <if test='user.email != null and user.email != \"\"'>"
            + "       AND email = #{user.email}"
            + "   </if>"
            + "   <if test='user.phone != null and user.phone != \"\"'>"
            + "       AND phone = #{user.phone}"
            + "   </if>"
            + "</where>"
            + " ORDER BY id DESC"
            + " LIMIT #{offset}, #{pageSize}"
            + "</script>")
    List<User> findbyPage(User user, Integer offset, Integer pageSize);

    @Select("select count(id) from user")
    Integer countuser();

    @Select("select count(username) from user where username = #{username}")
    int countByUsername(String username);

    @Update("update `user` set `username` = #{username},`email` = #{email},`phone` = #{phone} where `id` = #{id}")
    void updateuser(User user);

    @Delete("<script>" +
         "DELETE FROM `user` WHERE id IN " +
         "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
         "   #{id}" +
         "</foreach>" +
         "</script>")
    void delUser(@Param("ids") List<Long> ids);

}
