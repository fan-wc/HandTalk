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
    public User userLogin(String passwordOrEmail, String passwordOrCode) {
        return null;
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
                msg = "注册出成功";
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
