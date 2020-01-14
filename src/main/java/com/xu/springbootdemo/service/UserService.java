package com.xu.springbootdemo.service;

import com.xu.springbootdemo.domain.User;

public interface UserService {

    User checkUser(User user);

    User getUserList(String userId);

    User getUserByName(String username);
}
