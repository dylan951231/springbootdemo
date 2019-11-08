package com.xu.springbootdemo;

import com.xu.springbootdemo.controller.BaseController;
import com.xu.springbootdemo.domain.User;
import com.xu.springbootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    UserService userServiceImpl;

    @Test
    public User testUser(User user){

        return userServiceImpl.checkUser(user);
    }
}
