package com.dagm.devtool.service;

import com.dagm.devtool.cache.StoreKey;
import com.dagm.devtool.model.BaseObject;

/**
 * 提供客户端所有的 redis 接口
 */
public interface RedisStoreClient {

    /**
     * 判断是否存在某key
     *
     * @param key redis key
     * @return java.lang.Boolean
     * @author Guimu
     * @date 2020/1/9
     */
    Boolean exists(StoreKey key);

    /**
     * 根据redis key 获取其value
     *
     * @return T 返回值
     * @author Guimu
     * @date 2020/1/9
     */
    <T> T get(StoreKey key);

    /**
     * 返回该key的类型
     *
     * @param key redis key
     * @return java.lang.String
     * @author Guimu
     * @date 2020/1/9
     */
    String type(StoreKey key);


    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param key redis key
     * @param expireInSeconds 初始化 key 的过期时间
     * @param defaultValue 初始化 key 的默认值
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为 defaultValue,然后再增加amount, 过期时间为 defaultExpire
     */
    Long incrBy(StoreKey key, long amount, int expireInSeconds, long defaultValue);

    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param key redis key
     * @param expireInSeconds 初始化 key 的过期时间
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount ,过期时间为 expireInSeconds
     */
    Long incrBy(StoreKey key, long amount, int expireInSeconds);

    /**
     * 自增函数, 默认过期时间为category上配置的过期时间
     *
     * @param amount 要增加的值
     * @param key redis key
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount，返回值 amount,过期时间为category 的配置时间
     */
    Long incrBy(StoreKey key, long amount);

    /**
     * @param amount 要增加的值
     * @param key redis key
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为0，然后再增加, 注意:该值无过期时间
     */
    Double incrByFloat(StoreKey key, double amount);

    /**
     * @param expireInSeconds 超时时间 , 如果过期时间小于等于 0 ,该key直接过期
     * @param key redis key
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean expire(StoreKey key, int expireInSeconds);

    /**
     * @param key redis key
     * @param unixTimeInSeconds Unix时间戳，代表key要过期的绝对时间
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean expireAt(StoreKey key, long unixTimeInSeconds);

    /**
     * @param key redis key
     * @param expireInMs 超时时间,单位——毫秒 , 如果过期时间小于等于 0 ,该key直接过期
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean pexpire(StoreKey key, long expireInMs);

    /**
     * @param key redis key
     * @return 以秒为单位，返回给定 key 的剩余生存时间 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     */
    Long ttl(StoreKey key);

    /**
     * @param key redis key
     * @return 以毫秒为单位，返回给定 key 的剩余生存时间 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     */
    Long pttl(StoreKey key);

    /**
     * 移除给定 key 的生存时间，将这个key从 易失的(带生存时间key) 转换成 持久的(一个不带生存时间、永不过期的key)
     *
     * @param key redis key
     * @return 当生存时间移除成功时，返回 true  如果 key 不存在或 key 没有设置生存时间，返回 false
     */
    Boolean persist(StoreKey key);

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 如果 key 不存在， APPEND 就简单地将给定 key 设为
     * value ，就像执行 SET key value 一样。 需要注意的一点：  append 的是不加任何前缀的value （其他的API
     * 客户端都默认添加一些前缀来做序列化以及压缩判断）， 所以获取的时候必须用  getBytes 获取，如果要转成String ，使用 UTF8
     *
     * @param key redis key
     * @param value redis value
     * @return 追加 value 之后， key 中字符串的长度。
     */
    Integer append(StoreKey key, String value);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 当 key 存在但不是字符串类型时，返回一个错误。
     *
     * 注意：此命令设置的value没有过期时间，如需过期，需单独设置过期时间
     *
     * @param key redis key
     * @param value redis value
     * @return 返回给定 key 的旧值。当 key 不存在时，返回 null 。
     */
    <T> T getSet(StoreKey key, BaseObject value);


    /**
     * 可设置过期时间(lua 实现的原子命令).
     *
     * @param key StoreKey
     * @param value 设置的value
     * @param expireInSeconds 过期时间
     * @return 旧值
     */
    <T> T getSet(StoreKey key, BaseObject value, int expireInSeconds);


    /**
     * 设置 Key 对应的值为 Value，并设置过期时间expire(默认不需要这个,category自带过期时间), 如果 Key 不存在则添加，如果 Key 已经存在则覆盖
     *
     * @param key redis key
     * @param value redis value
     * @param expireInSeconds 单位 秒
     */
    void set(StoreKey key, BaseObject value, int expireInSeconds);

    /**
     * 设置 Key 对应的值为 Value(当且仅当key不存在),并设置过期时间expire(默认不需要这个,category自带过期时间)
     *
     * @param key redis key
     * @param value redis value
     * @param expireInSeconds 单位 秒
     * @return 如果成功，返回 true 如果失败，返回 false
     */
    Boolean add(StoreKey key, BaseObject value, int expireInSeconds);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 不存在时才添加，如果 Key 已经存在，不改变现有的值
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 不存在且添加成功，返回 true 如果 Key 已经存在，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setnx(StoreKey key, BaseObject value, int expireInSeconds);


    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作。默认使用Category配置的过期时间
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @return 如果 Key 存在且添加成功，返回 true 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setxx(StoreKey key, BaseObject value);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 存在且添加成功，返回 true 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setxx(StoreKey key, BaseObject value, int expireInSeconds);
}
