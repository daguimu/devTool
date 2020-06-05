/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Guimu
 * @date 2020/06/05
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.elastic")
public class ElasticProperties {

    private String host;
    private String port;
}