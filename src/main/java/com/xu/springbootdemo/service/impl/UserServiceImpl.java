package com.xu.springbootdemo.service.impl;

import com.xu.springbootdemo.dao.UserMapper;
import com.xu.springbootdemo.domain.User;
import com.xu.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.selectByUserObject(user);
    }
}
