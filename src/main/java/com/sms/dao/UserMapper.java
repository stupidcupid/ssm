package com.sms.dao;

import com.sms.domain.User;

import java.util.List;

/**
 * Created by nanzhou on 2017/7/27.
 */
public interface UserMapper {


    User selectByPrimaryKey(Long id);

    List<User> selectList();

    User login(String name,String password);
}


