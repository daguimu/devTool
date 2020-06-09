/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.config;

import com.dagm.devtool.model.BaseObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Guimu
 * @date 2019/12/02
 */
@Getter
@Setter
public class MqConsumerConfig extends BaseObject {

    private String namesrvAddr;

    private Integer consumeThreadMin;

    private Integer consumeThreadMax;

    private Integer consumeMessageBatchMaxSize;
}