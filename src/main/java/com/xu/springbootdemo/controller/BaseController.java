package com.xu.springbootdemo.controller;

import com.xu.springbootdemo.domain.User;
import com.xu.springbootdemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    @Autowired
    UserService userServiceImpl;

    @RequestMapping("/")
    public String index(HttpServletRequest request){
      return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, User user, Model model){
        System.out.println(" -- -- --- login -- --- - ");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
        try {
            subject.login(token);
            return "admin";
        }catch (AccountException e){
            //1.1.1 CredentitalsException    凭证异常 包含以下子类
            //
            //IncorrectCredentialsException             不正确的凭证
            //ExpiredCredentialsException               凭证过期
            //

            model.addAttribute("loginMsg","用户不存在！");
            return "login";
        } catch (CredentialsException e){
            //1.1.2 AccountException       账号异常 包含以下子类
            //ConcurrentAccessException     并发访问异常（多个用户同时登录时抛出）
            //UnknownAccountException      未知的账号
            //ExcessiveAttemptsException    认证次数超过限制
            //DisabledAccountException       禁用的账号
            //LockedAccountException         账号被锁定
            //pportedTokenException            使用了不支持的Token
            model.addAttribute("loginMsg","密码错误 ！");
            return "login";
        }

    }
    @GetMapping("/user/{id}/{userName}")
    public User getUser(User user){
        return userServiceImpl.checkUser(user);
    }

    @GetMapping("/getUserList/{userId}")
    public User getUserList(@PathVariable String userId){
        User user = userServiceImpl.getUserList(userId);
        return user;
    }

    @RequestMapping("/user/authorize")
    @RequiresPermissions("user:authorize")
    public String get(){
        System.out.println("user:authorize");
        return "authorize";
    }
}
