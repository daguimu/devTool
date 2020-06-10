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
public class MqProducerConfig extends BaseObject {

    private String namesrvAddr;

    /**
     * 消息最大大小，默认4M
     */
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间，默认3秒
     */
    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    private Integer retryTimesWhenSendFailed;

    private String producerGroup;
}