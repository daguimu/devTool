/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.tempconfig;

import com.dagm.devtool.model.BaseObject;
import com.dagm.devtool.serializer.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Guimu
 * @date 2021/05/02
 */
@Configuration
public class RedisTemplateConfig {
    @Bean(name = "perTemplate")
    @ConditionalOnProperty(name = "spring.redis.enable", havingValue = "true")
    public RedisTemplate<String, BaseObject> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, BaseObject> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(BaseObject.class));
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(BaseObject.class));
        return redisTemplate;
    }
}