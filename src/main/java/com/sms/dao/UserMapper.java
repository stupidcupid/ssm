package com.sms.dao;

import com.sms.domain.User;

/**
 * Created by nanzhou on 2017/7/27.
 */
public interface UserMapper {


    User selectByPrimaryKey(Long id);

}


