/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.impl;

import com.dagm.devtool.cache.StoreKey;
import com.dagm.devtool.model.BaseObject;
import com.dagm.devtool.service.RedisStoreClient;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Guimu
 * @date 2020/01/09
 */
@Service(value = "redisStoreClient")
public class RedisStoreClientImpl implements RedisStoreClient {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, BaseObject> redisTemplate;

    @Override
    public Boolean exists(StoreKey key) {
        return redisTemplate.hasKey(key.getKey());
    }

    @Override
    public String type(StoreKey key) {
        DataType dataType = redisTemplate.type(key.getKey());
        return dataType == null ? null : dataType.name();
    }

    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds, long defaultValue) {
        return redisTemplate.opsForValue().increment(key.getKey());
    }

    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds) {
        Long val = redisTemplate.opsForValue().increment(key.getKey(), amount);
        redisTemplate.expire(key.getKey(), expireInSeconds, TimeUnit.SECONDS);
        return val;
    }

    @Override
    public Long incrBy(StoreKey key, long amount) {
        return redisTemplate.opsForValue().increment(key.getKey(), amount);
    }

    @Override
    public Double incrByFloat(StoreKey key, double amount) {
        return redisTemplate.opsForValue().increment(key.getKey(), amount);
    }

    @Override
    public Long decrBy(StoreKey key, long amount, int expireInSeconds, int defaultValue) {
        return null;
    }

    @Override
    public Long decrBy(StoreKey key, long amount, int expireInSeconds) {
        Long val = redisTemplate.opsForValue().decrement(key.getKey(), amount);
        redisTemplate.expire(key.getKey(), expireInSeconds, TimeUnit.SECONDS);
        return val;
    }

    @Override
    public Long decrBy(StoreKey key, long amount) {
        return redisTemplate.opsForValue().decrement(key.getKey(), amount);
    }

    @Override
    public Boolean expire(StoreKey key, int expireInSeconds) {
        return redisTemplate.expire(key.getKey(), expireInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public Boolean expireAt(StoreKey key, long unixTimeInSeconds) {
        return redisTemplate.expireAt(key.getKey(), new Date(unixTimeInSeconds * 1000));
    }

    @Override
    public Boolean pexpire(StoreKey key, long expireInMs) {
        return redisTemplate.expire(key.getKey(), expireInMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public Long ttl(StoreKey key) {
        return redisTemplate.getExpire(key.getKey());
    }

    @Override
    public Long pttl(StoreKey key) {
        return redisTemplate.getExpire(key.getKey(), TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean persist(StoreKey key) {
        return redisTemplate.persist(key.getKey());
    }

    @Override
    public Integer append(StoreKey key, String value) {
        return redisTemplate.opsForValue().append(key.getKey(), value);
    }

    @Override
    public <T> T getSet(StoreKey key, BaseObject value) {
        return (T) redisTemplate.opsForValue().getAndSet(key.getKey(), value);
    }

    @Override
    public <T> T getSet(StoreKey key, BaseObject value, int expireInSeconds) {
        return null;
    }


    @Override
    public Boolean set(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean compareAndSet(StoreKey key, Object expect, Object newValue,
        int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean compareAndSetBytes(StoreKey key, byte[] expect, byte[] newValue,
        int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean compareAndSet(StoreKey key, Object expect, Object newValue) {
        return null;
    }

    @Override
    public Boolean compareAndDelete(StoreKey key, Object expect) {
        return null;
    }

    @Override
    public Boolean compareAndDeleteBytes(StoreKey key, byte[] expect) {
        return null;
    }

    @Override
    public Boolean add(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean setnx(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean setxx(StoreKey key, Object value) {
        return null;
    }

    @Override
    public Boolean setxx(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    @Override
    public Future<Boolean> asyncSetBytes(StoreKey key, byte[] bytesValue) {
        return null;
    }
}