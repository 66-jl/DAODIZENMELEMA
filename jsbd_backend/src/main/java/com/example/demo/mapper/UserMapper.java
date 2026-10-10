package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.OperationLog;
import com.example.demo.entity.StudyExperience;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

public interface UserMapper {


    @Insert("insert into operation_log(operation_time, operation_content) VALUES (#{operationTime},#{operationContent})")
    void savelog(OperationLog operationLog);

    /*
    * 实体类属性名与字段不一致
    * //手动映射
    * @Results({
        @Result(column = "username",property = "name"),
    })
    *
    * //sql语句中对列名起别名select username name form user
    *
    * //在yml配置文件中打开驼峰命名的映射开关可以实现create_time与createTime的映射
    *
    * */


    @Select("select * from user where id = #{id} ORDER BY id ASC")
    User finduser(User user);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into `user` (`username`,`avatar_url`,`email`,`phone`) VALUES (#{username},#{avatarUrl},#{email},#{phone})")
    void Saveuser(User user);

    List<User> findbyPage(
            @Param("user") User user,
            @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize
    );

    @Select("select count(id) from user")
    Integer countuser();

    @Select("select count(username) from user where username = #{username}")
    int countByUsername(String username);

    @Update("update `user` set `username` = #{username},`email` = #{email},`phone` = #{phone} where `id` = #{id}")
    void updateuser(User user);

    @Delete("<script>"
            + "DELETE FROM `user` WHERE id IN "
            + "<foreach collection='ids' item='id' open='(' separator=',' close=')'>"
            + "   #{id}"
            + "</foreach>"
            + "</script>")
    void delUser(@Param("ids") List<Long> ids);


    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<User> findall();



    void insertStuExpr(@Param("exprlist") List<StudyExperience> exprlist);


}
