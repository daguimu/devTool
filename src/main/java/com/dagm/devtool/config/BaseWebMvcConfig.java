package com.dagm.devtool.config;

import com.dagm.devtool.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Guimu
 * @date 2018/04/18 18:52:16
 **/
@Configuration
public class BaseWebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 注册拦截器
     *
     * @author Guimu
     * @date 2020/1/10
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
