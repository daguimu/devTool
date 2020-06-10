/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.dagm.devtool.config.MqConsumerConfig;
import com.dagm.devtool.enums.RocketMqTipEnum;
import com.dagm.devtool.exceptions.CommonException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author Guimu
 * @date 2019/12/10
 */
@Getter
@Setter
@Slf4j
public class BaseMqPushConsumer extends DefaultMQPushConsumer {

    private String topic;

    private MqConsumerConfig mqConsumerConfig;


    @Override
    public void start() throws MQClientException {
        this.checkAndInit();
        /*
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */

        this.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /*
         * 设置消费模型，集群还是广播，默认为集群
         */

        //this.setMessageModel(MessageModel.CLUSTERING);

        /*
         * 设置一次消费消息的条数，默认为1条
         */
        //this.setConsumeMessageBatchMaxSize(mqConsumerConfig.getConsumeMessageBatchMaxSize());
        this.registerMessageListener((MessageListenerConcurrently) this.getMessageListener());
        this.subscribe(topic, "*");
        super.start();
        log.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",
            super.getConsumerGroup(),
            this.getTopic(), this.getNamesrvAddr());
    }

    private void checkAndInit() {
        if (StringUtils.isEmpty(this.getConsumerGroup())) {
            throw new CommonException(RocketMqTipEnum.GROUP_NAME_IS_BLANK);
        }
        if (StringUtils.isEmpty(mqConsumerConfig.getNamesrvAddr())) {
            throw new CommonException(RocketMqTipEnum.NAME_ADDR_IS_BLANK);
        }
        if (StringUtils.isEmpty(this.getTopic())) {
            throw new CommonException(RocketMqTipEnum.TOPICS_IS_NULL);
        }
        this.init();

    }

    private void init() {
        this.setConsumeThreadMax(mqConsumerConfig.getConsumeThreadMax());
        this.setConsumeThreadMin(mqConsumerConfig.getConsumeThreadMin());
        this.setConsumeMessageBatchMaxSize(mqConsumerConfig.getConsumeMessageBatchMaxSize());
        this.setNamesrvAddr(mqConsumerConfig.getNamesrvAddr());
    }
}