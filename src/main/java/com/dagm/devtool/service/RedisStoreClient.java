package com.dagm.devtool.service;

import com.dagm.devtool.cache.StoreKey;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 提供客户端所有的 redis 接口
 */
public interface RedisStoreClient {

    /**
     * @return 是否存在该key，存在返回true，不存在返回false
     */
    Boolean exists(StoreKey key);

    /**
     * @param key key
     * @return 返回该key的类型
     */
    String type(StoreKey key);


    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param expireInSeconds 初始化 key 的过期时间
     * @param defaultValue 初始化 key 的默认值
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为 defaultValue,然后再增加amount, 过期时间为 defaultExpire
     */
    Long incrBy(StoreKey key, long amount, int expireInSeconds, long defaultValue);


    /**
     * 带有过期时间的incr操作
     *
     * @param amount 要增加的值
     * @param expireInSeconds 初始化 key 的过期时间
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount ,过期时间为 expireInSeconds
     */
    Long incrBy(StoreKey key, long amount, int expireInSeconds);

    /**
     * 自增函数, 默认过期时间为category上配置的过期时间
     *
     * @param amount 要增加的值
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key : 值=amount，返回值 amount,过期时间为category 的配置时间
     */
    Long incrBy(StoreKey key, long amount);

    /**
     * @param amount 要增加的值
     * @return 增长后 key 的值,如果 Key 不存在，会创建这个 Key，且值为0，然后再增加, 注意:该值无过期时间
     */
    Double incrByFloat(StoreKey key, double amount);

    /**
     * 带有过期时间的自减操作
     *
     * @param amount 要减少的值
     * @param expireInSeconds 初始化 key 的过期时间
     * @param defaultValue 初始化 key 的默认值
     * @return 减少后的值，如果 Key 不存在，会创建这个 Key，且值为 defaultValue,过期时间为 expireInSeconds，然后再减少
     */
    Long decrBy(StoreKey key, long amount, int expireInSeconds, int defaultValue);

    /**
     * 带有过期时间的自减操作
     *
     * @param amount 要减少的值
     * @param expireInSeconds 初始化 key 的过期时间
     * @return 减少后的值，如果 Key 不存在，会创建这个 Key : 值= -amount，返回值 -amount,过期时间为expireInSeconds
     */
    Long decrBy(StoreKey key, long amount, int expireInSeconds);

    /**
     * 自减函数, 默认过期时间为category上配置的过期时间
     *
     * @param amount 要减少的值
     * @return 减少后的值，如果 Key 不存在，会创建这个 Key : 值= -amount，返回值 -amount,过期时间为 category 的配置时间
     */
    Long decrBy(StoreKey key, long amount);

    /**
     * @param expireInSeconds 超时时间 , 如果过期时间小于等于 0 ,该key直接过期
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean expire(StoreKey key, int expireInSeconds);

    /**
     * @param unixTimeInSeconds Unix时间戳，代表key要过期的绝对时间
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean expireAt(StoreKey key, long unixTimeInSeconds);

    /**
     * @param expireInMs 超时时间,单位——>毫秒 , 如果过期时间小于等于 0 ,该key直接过期
     * @return 设置成功返回true，当key不存在或者不能为key设置生存时间时返回false
     */
    Boolean pexpire(StoreKey key, long expireInMs);

    /**
     * @return 以秒为单位，返回给定 key 的剩余生存时间<br> 当 key 不存在时，返回 -2 。<br> 当 key 存在但没有设置剩余生存时间时，返回 -1 。<br>
     */
    Long ttl(StoreKey key);

    /**
     * @return 以毫秒为单位，返回给定 key 的剩余生存时间<br> 当 key 不存在时，返回 -2 。<br> 当 key 存在但没有设置剩余生存时间时，返回 -1 。<br>
     */
    Long pttl(StoreKey key);

    /**
     * 移除给定 key 的生存时间，将这个key从 易失的(带生存时间key) 转换成 持久的(一个不带生存时间、永不过期的key)
     *
     * @return 当生存时间移除成功时，返回 true <br> 如果 key 不存在或 key 没有设置生存时间，返回 false <br>
     */
    Boolean persist(StoreKey key);

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。<br> 如果 key 不存在， APPEND 就简单地将给定 key 设为
     * value ，就像执行 SET key value 一样。<br> 需要注意的一点：  append 的是不加任何前缀的value （其他的API
     * 客户端都默认添加一些前缀来做序列化以及压缩判断）， 所以获取的时候必须用  getBytes 获取，如果要转成String ，使用 UTF8
     *
     * @return 追加 value 之后， key 中字符串的长度。
     */
    Long append(StoreKey key, String value);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。<br> 当 key 存在但不是字符串类型时，返回一个错误。
     *
     * 注意：此命令设置的value没有过期时间，如需过期，需单独设置过期时间
     *
     * @return 返回给定 key 的旧值。当 key 不存在时，返回 null 。
     */
    <T> T getSet(StoreKey key, Object value);

    byte[] getSetBytes(StoreKey key, byte[] value);

    /**
     * 同 {@link RedisStoreClient#getSet(StoreKey, Object)}, 可设置过期时间(lua 实现的原子命令).
     *
     * @param key StoreKey
     * @param value 设置的value
     * @param expireInSeconds 过期时间
     * @return 旧值
     */
    <T> T getSet(StoreKey key, Object value, int expireInSeconds);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 <br> 当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。<br>
     *
     * @param offset 偏移
     * @return 字符串值指定偏移量上的位(bit)如果是1返回true，否则返回false
     */
    Boolean getBit(StoreKey key, long offset);

    /**
     * 返回多个offset对应的位 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 <br>
     *
     * @param key storeKey
     * @param offsets 多个偏移
     * @return 返回多个offset对应的位集合
     */
    Map<Long, Boolean> getBits(StoreKey key, List<Long> offsets);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。位的设置或清除取决于 value 参数，可以是 0 也可以是 1 。<br> 当 key
     * 不存在时，自动生成一个新的字符串值。字符串会进行伸展(grown)以确保它可以将 value 保存在指定的偏移量上。<br> 当字符串值进行伸展时，空白位置以 0 填充。offset
     * 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。<br>
     *
     * @param offset 偏移
     * @param value 设置的值
     * @return 指定偏移量原来储存的值
     */
    Boolean setBit(StoreKey key, long offset, boolean value);

    /**
     * 设置一个key上的多个偏移量值
     *
     * @param key storeKey
     * @param bitsMap 需要设置的偏移量以及对应位置的值
     * @return 指定偏移量原来储存的值
     * @see RedisStoreClient#setBit(StoreKey, long, boolean)
     */
    Map<Long, Boolean> setBits(StoreKey key, Map<Long, Boolean> bitsMap);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     *
     * ************************************注意******************************* 这里的 start，stop 是以 byte
     * 为单位，并非bit为单位。 比如使用了setBit（key，5，true），index = 5。那么 此时如果是 bitCount（key，4，5）= 0, bitCount(key,
     * 0, 1) = 1; 此为redis 原生语义。 ***********************************************************************
     *
     * @param start 起始位置
     * @param end 结束位置
     * @return 字符串中设置为 1 的比特位的数量
     */
    Long bitCount(StoreKey key, long start, long end);

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     *
     * @return 字符串中设置为 1 的比特位的数量
     */
    Long bitCount(StoreKey key);

    /**
     * 设置 Key 对应的值为 Value，并设置过期时间expire(默认不需要这个,category自带过期时间), 如果 Key 不存在则添加，如果 Key 已经存在则覆盖
     *
     * @param expireInSeconds 单位 秒
     * @return 如果成功，返回 true<br> 如果失败，返回 false
     * 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean set(StoreKey key, Object value, int expireInSeconds);

    /**
     * 如果当前key 存在并且与 expect 相同, 则设置该key 的值为 newValue,并设置过期时间 expireInSeconds 该API为原子操作
     *
     * @param expect 期望的value, 可以为 null ,表示不存在
     * @param newValue 如果key符合期望,则设置该key 为 setValue, 不能为null
     * @param expireInSeconds 设置newValue的同时设置过期时间  小于0 则不过期.  单位:秒
     * @return {@code true} 成功， {@code false} 失败（当前value 与 expect不相同）
     */
    Boolean compareAndSet(StoreKey key, Object expect, Object newValue, int expireInSeconds);

    /**
     * {@link RedisStoreClient#compareAndSet(StoreKey, Object, Object, int)}
     */
    Boolean compareAndSetBytes(StoreKey key, byte[] expect, byte[] newValue, int expireInSeconds);

    /**
     * 如果当前key 存在并且与 expect 相同, 则设置该key 的值为 newValue,过期时间根据category的配置而定 该操作为原子操作
     *
     * @param expect 期望的value
     * @param newValue 如果key符合期望,则设置该key 为 setValue
     * @return {@code true} 成功， {@code false} 失败（当前value 与 expect不相同）
     */
    Boolean compareAndSet(StoreKey key, Object expect, Object newValue);

    /**
     * 如果当前key 存在并且 value 与 expect 相同,则删除该key. 该操作为原子操作
     *
     * @param expect 期望的value
     * @return {@code true} 成功， {@code false} 失败（当前value 与 expect不相同）
     */
    Boolean compareAndDelete(StoreKey key, Object expect);

    /**
     * {@link RedisStoreClient#compareAndDelete(StoreKey, Object)}
     */
    Boolean compareAndDeleteBytes(StoreKey key, byte[] expect);

    /**
     * 设置 Key 对应的值为 Value(当且仅当key不存在),并设置过期时间expire(默认不需要这个,category自带过期时间)
     *
     * @param expireInSeconds 单位 秒
     * @return 如果成功，返回 true<br> 如果失败，返回 false
     */
    Boolean add(StoreKey key, Object value, int expireInSeconds);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 不存在时才添加，如果 Key 已经存在，不改变现有的值 {@link RedisStoreClient#add(StoreKey,
     * Object, int)}
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 不存在且添加成功，返回 true<br> 如果 Key 已经存在，返回 false 如：如果需要捕获超时异常，可以捕获
     * StoreTimeoutException
     */
    Boolean setnx(StoreKey key, Object value, int expireInSeconds);


    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作。默认使用Category配置的过期时间
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @return 如果 Key 存在且添加成功，返回 true<br> 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setxx(StoreKey key, Object value);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 存在时才添加，如果 Key 不存在，则不会进行操作
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 存在且添加成功，返回 true<br> 如果操作失败，返回 false 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setxx(StoreKey key, Object value, int expireInSeconds);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 不存在时才添加，如果 Key 已经存在，不改变现有的值 {@link RedisStoreClient#add(StoreKey,
     * Object, int)}
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @param expireInSeconds 过期时间
     * @return 如果 Key 不存在且添加成功，返回 true<br> 如果 Key 已经存在，返回 false 如：如果需要捕获超时异常，可以捕获
     * StoreTimeoutException
     */
    Boolean setnxBytes(StoreKey key, byte[] value, int expireInSeconds);

    /**
     * 添加 Key 对应的值为 Value，只有当 Key 不存在时才添加，如果 Key 已经存在，不改变现有的值 过期时间以Category配置的为准 {@link
     * RedisStoreClient#setnxBytes(StoreKey, byte[], int)}
     *
     * @param key 要添加的  Key
     * @param value 要添加的 Value
     * @return 如果 Key 不存在且添加成功，返回 true<br> 如果 Key 已经存在，返回 false
     * 如：如果需要捕获超时异常，可以捕获 StoreTimeoutException
     */
    Boolean setnxBytes(StoreKey key, byte[] value);

    /**
     * 设置 Key 对应的值为 Value，此value不会进行序列化,以原始的方式存入redis 此接口需与  getRaw 搭配使用， 不可以用 get ， MultiGet 等方式获取
     */
    @Deprecated
    Boolean setRaw(StoreKey key, String value, int expireInSeconds);

    /**
     * 返回 Key 对应 原始值，此value不会进行反序列化
     *
     * @return string 类型
     */
    @Deprecated
    String getRaw(StoreKey key);

    /**
     * 设置 Key 对应的值为 Value，此value不会进行序列化,以原始的方式存入redis
     *
     * @return {@code true} 设置成功 {@code false} 设置失败
     */
    Boolean setBytes(StoreKey key, byte[] value, int expireInSeconds);

    /**
     * 设置 Key 对应的值为 Value，此value不会进行序列化,以原始的方式存入redis 过期时间为 category 配置的过期时间
     *
     * @return {@code true} 设置成功 {@code false} 设置失败
     */
    Boolean setBytes(StoreKey key, byte[] value);


    /**
     * 异步设置 Key 对应的值为 bytesValue(客户端不做多余序列化)，如果 Key 不存在则添加，如果 Key 已经存在则覆盖
     *
     * @param key 要设置的 Key
     * @param bytesValue 要设置的 bytesValue
     * @return 返回 Future 对象<br> 如果操作成功，Future 返回 true<br> 如果操作失败，Future 返回 false
     */
    Future<Boolean> asyncSetBytes(StoreKey key, byte[] bytesValue);
}
