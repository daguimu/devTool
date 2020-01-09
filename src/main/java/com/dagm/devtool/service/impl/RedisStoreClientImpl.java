/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.impl;

import com.dagm.devtool.cache.StoreKey;
import com.dagm.devtool.service.RedisStoreClient;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import org.springframework.stereotype.Service;

/**
 * @author Guimu
 * @date 2020/01/09
 */
@Service(value = "redisStoreClient")
public class RedisStoreClientImpl implements RedisStoreClient {

    @Override
    public Boolean exists(StoreKey key) {
        return null;
    }

    @Override
    public String type(StoreKey key) {
        return null;
    }

    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds, long defaultValue) {
        return null;
    }

    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds) {
        return null;
    }

    @Override
    public Long incrBy(StoreKey key, long amount) {
        return null;
    }

    @Override
    public Double incrByFloat(StoreKey key, double amount) {
        return null;
    }

    @Override
    public Long decrBy(StoreKey key, long amount, int expireInSeconds, int defaultValue) {
        return null;
    }

    @Override
    public Long decrBy(StoreKey key, long amount, int expireInSeconds) {
        return null;
    }

    @Override
    public Long decrBy(StoreKey key, long amount) {
        return null;
    }

    @Override
    public Boolean expire(StoreKey key, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean expireAt(StoreKey key, long unixTimeInSeconds) {
        return null;
    }

    @Override
    public Boolean pexpire(StoreKey key, long expireInMs) {
        return null;
    }

    @Override
    public Long ttl(StoreKey key) {
        return null;
    }

    @Override
    public Long pttl(StoreKey key) {
        return null;
    }

    @Override
    public Boolean persist(StoreKey key) {
        return null;
    }

    @Override
    public Long append(StoreKey key, String value) {
        return null;
    }

    @Override
    public <T> T getSet(StoreKey key, Object value) {
        return null;
    }

    @Override
    public byte[] getSetBytes(StoreKey key, byte[] value) {
        return new byte[0];
    }

    @Override
    public <T> T getSet(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean getBit(StoreKey key, long offset) {
        return null;
    }

    @Override
    public Map<Long, Boolean> getBits(StoreKey key, List<Long> offsets) {
        return null;
    }

    @Override
    public Boolean setBit(StoreKey key, long offset, boolean value) {
        return null;
    }

    @Override
    public Map<Long, Boolean> setBits(StoreKey key, Map<Long, Boolean> bitsMap) {
        return null;
    }

    @Override
    public Long bitCount(StoreKey key, long start, long end) {
        return null;
    }

    @Override
    public Long bitCount(StoreKey key) {
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
    public Boolean setnxBytes(StoreKey key, byte[] value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean setnxBytes(StoreKey key, byte[] value) {
        return null;
    }

    @Override
    public Boolean setRaw(StoreKey key, String value, int expireInSeconds) {
        return null;
    }

    @Override
    public String getRaw(StoreKey key) {
        return null;
    }

    @Override
    public Boolean setBytes(StoreKey key, byte[] value, int expireInSeconds) {
        return null;
    }

    @Override
    public Boolean setBytes(StoreKey key, byte[] value) {
        return null;
    }

    @Override
    public Future<Boolean> asyncSetBytes(StoreKey key, byte[] bytesValue) {
        return null;
    }
}