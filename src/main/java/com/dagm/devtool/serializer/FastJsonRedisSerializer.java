package com.dagm.devtool.serializer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final static ParserConfig DEFAULT_REDIS_CONFIG = new ParserConfig();

    static {
        DEFAULT_REDIS_CONFIG.setAutoTypeSupport(true);
    }

    /**
     * DEFAULT_CHARSET
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * clazz 反序列化类
     */
    private Class<T> clazz;

    /**
     * @param clazz 反序列化目标类
     * @Description: 构造器
     */
    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * Description: 序列化
     *
     * @param t 将要被序列化的对象
     * @return 返回序列化的二进制数组
     * @author Guimu
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSONObject.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }


    /**
     * Description: 反序列化
     *
     * @param bytes 将要反序列化的二进制数组
     * @return 反序列化后的结果
     * @author Guimu
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSONObject.parseObject(str, clazz, DEFAULT_REDIS_CONFIG);
    }

}
