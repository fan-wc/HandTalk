package com.handtalk.fan.controller;

import com.aliyuncs.exceptions.ClientException;
import com.handtalk.fan.models.User;
import com.handtalk.fan.service.UserService;
import com.handtalk.fan.utils.SendCodeEmail;
import com.handtalk.fan.utils.SendCodePhone;
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
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public Map sendCode(HttpServletRequest request, @RequestBody User user) throws ClientException, Exception{
        Map resMap = new HashMap();
        String msg = "";
        if (user.getUserPhone() == null & user.getUserEmail() == null){
            msg = "手机号或邮箱账号为空";
        }else {
            if (user.getUserPhone()!= null){
                SendCodePhone.sendSms(user.getUserPhone());
                msg = "发送成功";
            }else {

            }
        }
        resMap.put("msg",msg);
        return resMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map userLoginCode(HttpServletRequest request) {
        String phoneOrEmail = request.getParameter("phoneOrEmail");
        String passwordOrCode = request.getParameter("pwdOrCode");
        Map resMap = new HashMap();
        resMap = userService.userLogin(phoneOrEmail, passwordOrCode);
        return resMap;
    }
}
