package com.xu.springbootdemo.authorize.service;


import com.xu.springbootdemo.authorize.domain.Permission;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface AuthorizeService {
    LinkedList<Permission> getAllPermissions();

    List<Permission> getUserAuthorize(Integer id);

    int updataAuthroizeOperation(HashMap param);
}
