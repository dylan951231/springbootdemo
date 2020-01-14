package com.xu.springbootdemo.authorize.service.impl;

import com.xu.springbootdemo.authorize.dao.AuthorizDao;
import com.xu.springbootdemo.authorize.domain.Permission;
import com.xu.springbootdemo.authorize.service.AuthorizeService;
import com.xu.springbootdemo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    AuthorizDao authorizDao;

    @Override
    public LinkedList<Permission> getAllPermissions() {
        return authorizDao.getAllPermissions();
    }

    @Override
    public List<Permission> getUserAuthorize(Integer id) {
        List<Permission> o = authorizDao.getUserAuthorize(id);
        return o;
    }

    @Override
    @Transactional
    public int updataAuthroizeOperation(HashMap param) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        Integer userRole = principal.getUserRole();
        if(userRole==null){
            throw new NullPointerException();
        }
        int del = authorizDao.deleteRolePremissionByRoleId(userRole);

        int insertUser = authorizDao.insertIntoRolePremissionByRole(userRole,(ArrayList)param.get("user"));
        int insertPro = authorizDao.insertIntoRolePremissionByRole(userRole,(ArrayList)param.get("pro"));

        return 0;
    }
}
