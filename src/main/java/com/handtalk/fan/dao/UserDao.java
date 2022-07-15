package com.handtalk.fan.dao;

import com.handtalk.fan.models.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
    public Integer addUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(Integer id);
    public User getUser(User user);
}
