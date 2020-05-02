package com.cyl.flashsaleuser.controller;


import com.cyl.flashsalestock.entity.User;
import com.cyl.flashsaleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("/log")
    public Map log(@RequestBody User user) {
        return userService.login(user);
    }

    //用户注册
    @RequestMapping("/reg")
    public Map reg(@RequestBody User user) {
        return userService.regist(user);
    }
}
