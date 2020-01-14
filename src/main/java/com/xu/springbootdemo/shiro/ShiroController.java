package com.xu.springbootdemo.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ShiroController {

    @GetMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/toAdmin")
    public String toAdmin(){
        return "admin";
    }

    @GetMapping("/toError")
    public String toError(){
        return "error";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "无权限";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "权限认证失败";
    }
}
