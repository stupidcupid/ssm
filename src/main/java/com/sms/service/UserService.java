package com.sms.service;

import com.sms.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by nanzhou on 2017/7/27.
 */
public interface UserService {

    User getById(Long id);
}
