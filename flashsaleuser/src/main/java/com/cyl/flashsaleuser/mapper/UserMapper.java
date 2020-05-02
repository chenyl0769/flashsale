package com.cyl.flashsaleuser.mapper;

import com.cyl.flashsalestock.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户
     * @param name 用户名
     * @param pwd 密码
     * @return 用户
     */
    @Select("select user_id,user_name,user_pwd from users where user_name=#{name} and user_pwd=#{pwd}")
    @Results({
            @Result(property = "user_Id",column = "user_id" ,javaType = String.class),
            @Result(property = "user_name",column = "user_name" ,javaType = String.class),
            @Result(property = "user_pwd",column = "user_pwd" ,javaType = String.class)
    })
    User getUser (@Param("name") String name, @Param("pwd")String pwd);

    /***
     * 查询用户名是否存在
     * @param name 用户名
     * @return 记录数
     */
    @Select("select count(1) from users where user_name=#{name}")
    int getUserByName (@Param("name") String name);


    /**
     * 用户注册
     * @param name 用户名
     * @param pwd 密码
     * @return 插入数据条数
     */

    @Insert({"insert into users (user_id,user_name,user_pwd) values (#{id},#{name},#{pwd})"})
    int addUer (@Param("id")String id, @Param("name") String name ,@Param("pwd")String pwd);
}
