package com.sms.service.impl;

import com.sms.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nanzhou on 2017/9/1.
 */
public class RedisServiceImpl implements RedisService {


    @Autowired
    protected RedisTemplate redisTemplate;


}