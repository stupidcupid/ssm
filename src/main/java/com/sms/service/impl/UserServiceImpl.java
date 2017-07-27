package com.sms.service.impl;

import com.sms.dao.UserMapper;
import com.sms.domain.User;
import com.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nanzhou on 2017/7/27.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userDao;

    public User getById(Long id) {

        return userDao.selectByPrimaryKey(id);

    }


}
