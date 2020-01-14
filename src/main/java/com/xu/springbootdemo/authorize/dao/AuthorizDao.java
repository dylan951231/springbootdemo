package com.xu.springbootdemo.authorize.dao;

import com.xu.springbootdemo.authorize.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Mapper
@Repository
public interface AuthorizDao {
    LinkedList<Permission> getAllPermissions();

    List<Permission> getUserAuthorize(@Param("id") Integer id);

    int deleteRolePremissionByRoleId(@Param("userRole") Integer userRole);

    int insertIntoRolePremissionByRole(@Param("userRole")Integer userRole,@Param("list") ArrayList user);
}
