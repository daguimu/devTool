package com.dagm.devtool.serializer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private final static ParserConfig defaultRedisConfig = new ParserConfig();

    static {
        defaultRedisConfig.setAutoTypeSupport(true);
    }

    /**
     * DEFAULT_CHARSET <br>
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * clazz 反序列化类<br>
     */
    private Class<T> clazz;

    /**
     * <Description> 构造器<br>
     *
     * @param clazz 反序列化目标类
     */
    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * Description: 序列化<br>
     *
     * @param t
     * @return
     * @throws SerializationException <br>
     * @author xubin<br>
     * @taskId <br>
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSONObject.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }


    /**
     * Description: 反序列化<br>
     *
     * @param bytes
     * @return
     * @throws SerializationException <br>
     * @author xubin<br>
     * @taskId <br>
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
//        return (T) JSON.parseObject(str, clazz, defaultRedisConfig);
        return (T) JSONObject.parseObject(str, clazz, defaultRedisConfig);
    }

}
