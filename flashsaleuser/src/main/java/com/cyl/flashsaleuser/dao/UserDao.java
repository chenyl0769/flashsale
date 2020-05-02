package com.cyl.flashsaleuser.dao;

import com.cyl.flashsalestock.entity.User;

public interface UserDao {

    User login (User user);

    int regist(User user);

    int registcheck (User user);
}
