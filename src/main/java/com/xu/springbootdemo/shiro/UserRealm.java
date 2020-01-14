package com.xu.springbootdemo.shiro;

import com.xu.springbootdemo.domain.User;
import com.xu.springbootdemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("---------------》》授权");
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List list =new ArrayList();
        Set role = new HashSet();
        role.add("admin");
        Set permission = new HashSet();
        permission.add("user:authorize");
        simpleAuthorizationInfo.addRoles(role);
        if(user.getUserName().equals("admin")){
            simpleAuthorizationInfo.addStringPermissions(permission);

        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("---------------》》身份验证");


        //判断用户名是否和token中的是否匹配，如果不匹配就 return null,并且抛出用户不存在异常
        UsernamePasswordToken token  = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByName(token.getUsername());

        if(user ==null ){
            return  null;
        }
        //如果用户存在，就返回SimpleAuthenticationInfo()，并将password作为第二个参数返回,此处user为授权做准备
        return new SimpleAuthenticationInfo(user,user.getUserPassword(),"");
    }
}
