/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.impl;

import com.dagm.devtool.cache.StoreKey;
import com.dagm.devtool.service.RedisStoreClient;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author Guimu
 * @date 2020/01/09
 */
@Service(value = "redisStoreClient")
public class RedisStoreClientImpl implements RedisStoreClient {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断是否存在某key
     *
     * @param key redis key
     * @return java.lang.Boolean
     * @author Guimu
     * @date 2020/1/9
     */
    @Override
    public Boolean exists(StoreKey key) {
        return redisTemplate.hasKey(key.getKey());
    }

    /**
     * 根据redis key 获取其value
     *
     * @author Guimu
     * @date 2020/1/9
     */
    @Override
    public <T> T get(StoreKey key) {
        return (T) redisTemplate.opsForValue().get(key.getKey());
    }

    /**
     * 返回该key的类型
     *
     * @param key redis key
     * @return java.lang.String
     * @author Guimu
     * @date 2020/1/9
     */
    @Override
    public String type(StoreKey key) {
        DataType dataType = redisTemplate.type(key.getKey());
        return dataType == null ? null : dataType.name();
    }


    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param key redis key
     * @param expireInSeconds 初始化 key 的过期时间
     * @param defaultValue 初始化 key 的默认值
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为 defaultValue,然后再增加amount, 过期时间为 defaultExpire
     */
    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds, long defaultValue) {
        return redisTemplate.opsForValue().increment(key.getKey());
    }

    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param key redis key
     * @param expireInSeconds 初始化 key 的过期时间
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount ,过期时间为 expireInSeconds
     */
    @Override
    public Long incrBy(StoreKey key, long amount, int expireInSeconds) {
        Long val = redisTemplate.opsForValue().increment(key.getKey(), amount);
        redisTemplate.expire(key.getKey(), expireInSeconds, TimeUnit.SECONDS);
        return val;
    }

    @Override
    public Boolean delete(StoreKey key) {
        return redisTemplate.delete(key.getKey());
    }


    /**
     * 自增函数, 默认过期时间为category上配置的过期时间
     *
     * @param amount 要增加的值
     * @param key redis key
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount，返回值 amount,过期时间为category 的配置时间
     */
    @Override
    public Long incrBy(StoreKey key, long amount) {
        return redisTemplate.opsForValue().increment(key.getKey(), amount);
    }

    /**
     * @param amount 要增加的值
     * @param key redis key
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为0，然后再增加, 注意:该值无过期时间
     */
    @Override
    public Double incrByFloat(StoreKey key, double amount) {
        return redisTemplate.opsForValue().increment(key.getKey(), amount);
    }


    /**
     * @param expireInSeconds 超时时间 , 如果过期时间小于等于 0 ,该key直接过期
     * @param key redis key
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    @Override
    public Boolean expire(StoreKey key, int expireInSeconds) {
        return redisTemplate.expire(key.getKey(), expireInSeconds, TimeUnit.SECONDS);
    }

    /**
     * @param key redis key
     * @param unixTimeInSeconds Unix时间戳，代表key要过期的绝对时间
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    @Override
    public Boolean expireAt(StoreKey key, long unixTimeInSeconds) {
        return redisTemplate.expireAt(key.getKey(), new Date(unixTimeInSeconds * 1000));
    }

    /**
     * @param key redis key
     * @param expireInMs 超时时间,单位——毫秒 , 如果过期时间小于等于 0 ,该key直接过期
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    @Override
    public Boolean pexpire(StoreKey key, long expireInMs) {
        return redisTemplate.expire(key.getKey(), expireInMs, TimeUnit.MILLISECONDS);
    }

    /**
     * @param key redis key
     * @return 以秒为单位，返回给定 key 的剩余生存时间 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     */
    @Override
    public Long ttl(StoreKey key) {
        return redisTemplate.getExpire(key.getKey());
    }


    /**
     * @param key redis key
     * @return 以毫秒为单位，返回给定 key 的剩余生存时间 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     */
    @Override
    public Long pttl(StoreKey key) {
        return redisTemplate.getExpire(key.getKey(), TimeUnit.MILLISECONDS);
    }


    /**
     * 移除给定 key 的生存时间，将这个key从 易失的(带生存时间key) 转换成 持久的(一个不带生存时间、永不过期的key)
     *
     * @param key redis key
     * @return 当生存时间移除成功时，返回 true  如果 key 不存在或 key 没有设置生存时间，返回 false
     */
    @Override
    public Boolean persist(StoreKey key) {
        return redisTemplate.persist(key.getKey());
    }

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 如果 key 不存在， APPEND 就简单地将给定 key 设为
     * value ，就像执行 SET key value 一样。 需要注意的一点：  append 的是不加任何前缀的value （其他的API
     * 客户端都默认添加一些前缀来做序列化以及压缩判断）， 所以获取的时候必须用  getBytes 获取，如果要转成String ，使用 UTF8
     *
     * @param key redis key
     * @param value redis value
     * @return 追加 value 之后， key 中字符串的长度。
     */
    @Override
    public Integer append(StoreKey key, String value) {
        return redisTemplate.opsForValue().append(key.getKey(), value);
    }


    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 当 key 存在但不是字符串类型时，返回一个错误。
     *
     * 注意：此命令设置的value没有过期时间，如需过期，需单独设置过期时间
     *
     * @param key redis key
     * @param value redis value
     * @return 返回给定 key 的旧值。当 key 不存在时，返回 null 。
     */
    @Override
    public <T> T getSet(StoreKey key, Object value) {
        return (T) redisTemplate.opsForValue().getAndSet(key.getKey(), value);
    }

    /**
     * 可设置过期时间(lua 实现的原子命令).
     *
     * @param key StoreKey
     * @param value 设置的value
     * @param expireInSeconds 过期时间
     * @return 旧值
     */
    @Override
    public <T> T getSet(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }

    /**
     * 设置 Key 对应的值为 Value，并设置过期时间expire(默认不需要这个,category自带过期时间), 如果 Key 不存在则添加，如果 Key 已经存在则覆盖
     *
     * @param key redis key
     * @param value redis value
     * @param expireInSeconds 单位 秒
     */
    @Override
    public void set(StoreKey key, Object value, int expireInSeconds) {
        redisTemplate.opsForValue().set(key.getKey(), value);
    }


    /**
     * 设置 Key 对应的值为 Value(当且仅当key不存在),并设置过期时间expire(默认不需要这个,category自带过期时间)
     *
     * @param key redis key
     * @param value redis value
     * @param expireInSeconds 单位 秒
     * @return 如果成功，返回 true 如果失败，返回 false
     */
    @Override
    public Boolean add(StoreKey key, Object value, int expireInSeconds) {
        return null;
    }


    /**
     * 添加 Key 对应的值为 Value，只有当 Key 不存在时才添加，如果 Key 已经存在，不改变现有的值
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 不存在且添加成功，返回 true 如果 Key 已经存在，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    @Override
    public Boolean setnx(StoreKey key, Object value, int expireInSeconds) {
        return baseSetnx(key.getKey(), value, expireInSeconds * 1000, false);
    }

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作。默认使用Category配置的过期时间
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @return 如果 Key 存在且添加成功，返回 true 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    @Override
    public Boolean setxx(StoreKey key, Object value) {
        return redisTemplate.opsForValue().setIfPresent(key.getKey(), value);
    }


    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 存在且添加成功，返回 true 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    @Override
    public Boolean setxx(StoreKey key, Object value, int expireInSeconds) {
        return baseSetnx(key.getKey(), value, expireInSeconds * 1000, true);
    }

    private boolean baseSetnx(String key, Object value, int expire, boolean exist) {
        SessionCallback sc = new SessionCallback() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                //开始观察key
                operations.watch(key);
                //获取key 对应的值
                boolean flag = null == operations.opsForValue().get(key);
                List<Object> rs = null;
                //资源可用
                if (exist ^ flag) {
                    //开启事务
                    operations.multi();
                    //必要的查询
                    operations.opsForValue().get(key);
                    //占用资源
                    //并设置其值为l timeUnit的过期时间
                    if (expire == -1) {
                        operations.opsForValue().set(key, value);
                    } else {
                        operations.opsForValue()
                            .set(key, value, expire, TimeUnit.MILLISECONDS);
                    }
                    rs = operations.exec();
                }
                return rs;
            }
        };
        Object result = redisTemplate.execute(sc);
        return !CollectionUtils.isEmpty((List) result);
    }
}