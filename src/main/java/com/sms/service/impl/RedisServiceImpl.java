package com.sms.service.impl;

import com.sms.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by nanzhou on 2017/9/4.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 添加到redis
     *
     * @param key
     * @param extime  失效时间
     * @param content
     * @return
     */
    public boolean add(final String key, final long extime, final String content) {
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                connection.setEx(serializer.serialize(key), extime, serializer.serialize(content));
                return true;
            }
        });
        return result;
    }

    public List<String> getKeys(final String startWith) {
        List<String> result = (List<String>) redisTemplate.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                Set<byte[]> s = connection.keys(serializer.serialize(startWith + "*"));
                if (s != null && s.size() > 0) {
                    List<String> l = new ArrayList<String>();
                    for (byte[] b : s) {
                        l.add(serializer.deserialize(b));
                    }
                    return l;
                }
                return null;
            }
        });
        return result;
    }

    public boolean del(final String key) {
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                Long l = connection.del(serializer.serialize(key));
                if (l != null && l.longValue() > 0)
                    return true;
                else
                    return false;
            }
        });
        return result;
    }

    public boolean delBatch(final String keyStartWith) {
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();

                Set<byte[]> s = connection.keys(serializer.serialize(keyStartWith + "*"));
                if (s != null && s.size() > 0) {
                    List<String> l = new ArrayList<String>();
                    for (byte[] b : s) {
                        connection.del(b);
                    }
                }
                return true;
            }
        });
        return result;
    }

    public boolean add(final String key, final String content) {
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(content));
                return true;
            }
        });
        return result;
    }


    public boolean add(final String key, final long extime, final List<String> list) {
        if (list == null || list.size() == 0 || extime < 0)
            return false;

        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keybyte = serializer.serialize(key);
                for (String str : list) {
                    connection.lPush(keybyte, serializer.serialize(str));
                }
                connection.expire(keybyte, extime);
                return true;
            }
        });

        return result;
    }

    public  String get(final String keyId) {
        String result = (String) redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    public List<String> getList(final String keyId) {
        List<String> result = (List<String>) redisTemplate.execute(new RedisCallback<List<String>>() {
            public List<String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                List<byte[]> list = connection.lRange(key, 0, -1);
                if (list != null && list.size() > 0) {
                    List<String> l = new ArrayList<String>();
                    for (byte[] bytes : list) {
                        l.add(serializer.deserialize(bytes));
                    }
                    return l;
                }
                return null;
            }
        });
        return result;
    }

    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
}
