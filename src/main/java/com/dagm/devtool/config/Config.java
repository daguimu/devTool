package com.dagm.devtool.config;

import com.dagm.devtool.advice.GlobalDefultExceptionHandler;
import com.dagm.devtool.filter.RequestLogFilter;
import com.dagm.devtool.interceptor.InnerInterceptor;
import com.dagm.devtool.model.BaseObject;
import com.dagm.devtool.serializer.FastJsonRedisSerializer;
import com.dagm.devtool.service.impl.ElasticSearchStoreClientImpl;
import com.dagm.devtool.service.impl.RedisStoreClientImpl;
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
    ElasticSearchStoreClientImpl.class

})
public class Config extends CachingConfigurerSupport {

    @Bean(name = "redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.enable", havingValue = "true")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }
}

