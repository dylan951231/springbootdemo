package com.xu.springbootdemo.controller;

import com.xu.springbootdemo.domain.User;
import com.xu.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

    @Autowired
    UserService userServiceImpl;

    @GetMapping("/login")
    public void login(HttpServletRequest request){
        System.out.println(" -- -- --- login -- --- - ");
        request.getSession().setAttribute("user","login");
    }
    @ResponseBody
    @GetMapping("/user/{id}/{userName}")
    public User getUser(User user){
        return userServiceImpl.checkUser(user);
    }
}
