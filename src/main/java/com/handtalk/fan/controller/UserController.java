package com.handtalk.fan.controller;

import com.handtalk.fan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(path = "/regist", method = RequestMethod.POST)
    public Map register(HttpServletRequest request) {
        Map resMap = new HashMap();

        return resMap;
    }

    @RequestMapping("/registByCode")
    public Map registerByCode(HttpServletRequest request,@RequestBody Map<String, Object> map) {
        Map resMap = new HashMap();

        return resMap;
    }
}
