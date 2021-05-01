package com.dagm.devtool.config;

import com.dagm.devtool.advice.GlobalDefultExceptionHandler;
import com.dagm.devtool.filter.RequestLogFilter;
import com.dagm.devtool.interceptor.InnerInterceptor;
import com.dagm.devtool.service.impl.ElasticSearchStoreClientImpl;
import com.dagm.devtool.service.impl.RedisStoreClientImpl;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
}

