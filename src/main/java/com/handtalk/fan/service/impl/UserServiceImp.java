package com.handtalk.fan.service.impl;

import com.handtalk.fan.dao.UserDao;
import com.handtalk.fan.models.User;
import com.handtalk.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Map userLogin(String phoneOrEmail, String passwordOrCode) {
        Map resMap = new HashMap();
        User user = new User();
        user.setId(0);
        String msg = null;
        if (phoneOrEmail.endsWith("com")){
            user.setUserEmail(phoneOrEmail);
        }else {
            user.setUserPhone(phoneOrEmail);
        }
        System.out.println(user.toString());
        User regUser = userDao.getUser(user);
        if (regUser == null){
            msg = "该手机号或邮箱未注册";
            resMap.put("user",null);
        }else {
            if (regUser.getUserPwd().equals(passwordOrCode)){
                msg = "登陆成功";
                regUser.setUserPwd("****************************");
                resMap.put("user",regUser);
            }else {
                msg = "密码错误";
                resMap.put("user",null);
            }
        }
        resMap.put("msg",msg);
        return resMap;
    }

    @Override
    public Map userRegister(User user) {
        User regUser = new User();
        User regdUser = userDao.getUser(user);
        String msg = null;
        Map map = new HashMap<String, User>();
        if (regdUser == null){
            if(user.getUserPwd().equals(user.getPasswordConfirm())){
                userDao.addUser(user);
                msg = "注册成功";
                regUser = userDao.getUser(user);
            }else {
                msg = "两次密码不一致";
            }
        }else {
            msg = "该邮箱或手机号已注册";
        }
        map.put("msg",msg);
        map.put("user",regUser);
        return map;
    }

    @Override
    public Map userRegisterByCode(User user, HttpServletRequest request) {
        User regUser = new User();
        User regdUser = userDao.getUser(user);
        String msg = null;
        Map map = new HashMap<String, User>();
        String code = (String)request.getSession().getAttribute("code");
        if (code == null){
            msg = "验证码不可为空";
        }else {
            if (user.getCode().equals(code)){
                userDao.addUser(user);
                regUser = userDao.getUser(user);
            }else {
                msg = "验证码错误";
            }
        }
        map.put("msg",msg);
        map.put("user",regUser);
        return map;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
