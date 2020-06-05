package com.dagm.devtool.config;

import com.dagm.devtool.advice.GlobalDefultExceptionHandler;
import com.dagm.devtool.filter.RequestLogFilter;
import com.dagm.devtool.interceptor.InnerInterceptor;
import com.dagm.devtool.serializer.FastJson2JsonRedisSerializer;
import com.dagm.devtool.service.impl.EsStoreClientImpl;
import com.dagm.devtool.service.impl.RedisStoreClientImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@Import({
    WebConfiguration.class,
    RedisStoreClientImpl.class,
    GlobalDefultExceptionHandler.class,
    InnerInterceptor.class,
    RequestLogFilter.class,
    ElasticSearchConfig.class,
    EsStoreClientImpl.class

})
public class Config extends CachingConfigurerSupport {

    @Bean(name = "redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.enable", havingValue = "true")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        //Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        //使用Fastjson2JsonRedisSerializer来序列化和反序列化redis的value值
        FastJson2JsonRedisSerializer<Object> serializer = new FastJson2JsonRedisSerializer<>(
            Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}

