package com.dagm.devtool.serializer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    // FastJSON2 uses different configuration approach
    private final static JSONReader.Feature[] READ_FEATURES = {JSONReader.Feature.SupportAutoType};
    private final static JSONWriter.Feature[] WRITE_FEATURES = {JSONWriter.Feature.WriteClassName};

    /**
     * DEFAULT_CHARSET
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * clazz 反序列化类
     */
    private Class<T> clazz;

    /**
     * 构造器
     * @param clazz 反序列化目标类
     */
    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化
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
        return JSON.toJSONString(t, WRITE_FEATURES).getBytes(DEFAULT_CHARSET);
    }


    /**
     * 反序列化
     *
     * @param bytes 将要反序列化的二进制数组
     * @return T 反序列化后的结果
     * @author Guimu
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz, READ_FEATURES);
    }

}
