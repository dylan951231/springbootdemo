package com.xu.springbootdemo.dao;

import com.xu.springbootdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserObject(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserList(@Param("userId") String userId);

    User getUserByName(@Param("userName") String username);
}