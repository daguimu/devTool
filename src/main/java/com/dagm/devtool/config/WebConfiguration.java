/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.config;

import com.dagm.devtool.serializer.SensitiveInfoSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Guimu
 * @date 2019/10/14
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SimpleModule m = new SimpleModule();
        m.addSerializer(Object.class, new SensitiveInfoSerialize());
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().modules(m);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
}