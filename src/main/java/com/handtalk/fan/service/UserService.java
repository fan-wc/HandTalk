package com.handtalk.fan.service;

import com.handtalk.fan.models.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public interface UserService {
    public Map userLogin(String passwordOrEmail, String passwordOrCode);
    public Map userRegister(User user);
    public Map userRegisterByCode(User user, HttpServletRequest request);
    public User updateUser(User user);
}
