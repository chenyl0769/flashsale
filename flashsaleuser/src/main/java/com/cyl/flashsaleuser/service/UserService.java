package com.cyl.flashsaleuser.service;

import com.cyl.flashsalestock.entity.User;

import java.util.Map;

public interface UserService {

    Map login(User user);

    Map regist(User user);
}
