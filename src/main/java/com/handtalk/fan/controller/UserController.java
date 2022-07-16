package com.handtalk.fan.controller;

import com.handtalk.fan.models.User;
import com.handtalk.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(path = "/regist", method = RequestMethod.POST)
    public Map register(HttpServletRequest request, @RequestBody User user) {
        Map resMap = new HashMap();
        user.setId(null);
        if (user.getCode() == null){
            resMap = userService.userRegister(user);
        }else {
            resMap = userService.userRegisterByCode(user,request);
        }
        return resMap;
    }

    @RequestMapping(value = "/registByCode", method = RequestMethod.POST)
    public Map registerByCode(HttpServletRequest request) {
        Map resMap = new HashMap();

        return resMap;
    }
}
