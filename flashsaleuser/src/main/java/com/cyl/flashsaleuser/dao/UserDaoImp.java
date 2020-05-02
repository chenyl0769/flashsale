package com.cyl.flashsaleuser.dao;

import com.cyl.flashsalestock.entity.User;
import com.cyl.flashsaleuser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.getUser(user.getUser_name(),user.getUser_pwd());
    }

    @Override
    public int regist(User user) {
        return userMapper.addUer(user.getUser_Id(),user.getUser_name(),user.getUser_pwd());
    }

    @Override
    public int registcheck(User user) {
        return userMapper.getUserByName(user.getUser_name());
    }
}
