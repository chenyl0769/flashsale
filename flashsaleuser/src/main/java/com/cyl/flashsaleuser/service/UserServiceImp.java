package com.cyl.flashsaleuser.service;

import com.cyl.flashsalestock.entity.User;
import com.cyl.flashsalestock.util.MyResults;
import com.cyl.flashsaleuser.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public Map login(User user) {
        //判断用户是否存在返回结果
        User u = userDao.login(user);
        if (u==null) {
            return MyResults.getresult(false,"密码错误",null);
        }
        return MyResults.getresult(true,"登录成功",u);
    }

    @Override
    @Transactional
    public Map regist(User user) {
        //查询用户名是否已经存在
        int registcheck = userDao.registcheck(user);
        if (registcheck>0) {
            return MyResults.getresult(false,"用户已注册",null);
        }
        //生成用户id并插入数据库
        String user_id = UUID.randomUUID().toString();
        user.setUser_Id(user_id);
        int result= userDao.regist(user);
        if (result!=1) {
            return MyResults.getresult(false,"注册失败",null);
        }
        return MyResults.getresult(true,"注册成功",null);
    }
}
