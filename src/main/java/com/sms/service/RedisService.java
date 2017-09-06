package com.sms.service;

import java.util.List;

/**
 * Created by nanzhou on 2017/9/4.
 */
public interface RedisService {

    boolean add(final String key, final long extime, final String content);

    List<String> getKeys(final String startWith);

    boolean del(final String key);

    boolean delBatch(final String keyStartWith);

    boolean add(final String key, final String content);

    boolean add(final String key, final long extime, final List<String> list);

    String get(final String keyId);

    List<String> getList(final String keyId);

}
