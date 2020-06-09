/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.consumer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dagm.devtool.config.MqProducerConfig;
import com.dagm.devtool.enums.RocketMqTipEnum;
import com.dagm.devtool.exceptions.CommonException;
import java.io.UnsupportedEncodingException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.springframework.util.StringUtils;

/**
 * @author Guimu
 * @date 2019/12/10
 */
@Getter
@Setter
@Slf4j
public class BaseMqPushProducer<T> extends DefaultMQProducer {

    private MqProducerConfig mqProducerConfig;
    private String topic;


    @Override
    public void start() throws MQClientException {
        this.checkAndInit();
        super.start();
        log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
            , this.getProducerGroup(), this.getNamesrvAddr()));
    }

    private void checkAndInit() {
        if (StringUtils.isEmpty(mqProducerConfig.getProducerGroup())) {
            throw new CommonException(RocketMqTipEnum.GROUP_NAME_IS_BLANK);
        }
        if (StringUtils.isEmpty(mqProducerConfig.getNamesrvAddr())) {
            throw new CommonException(RocketMqTipEnum.NAME_ADDR_IS_BLANK);
        }
        this.init();
    }

    private void init() {
        this.setRetryTimesWhenSendFailed(mqProducerConfig.getRetryTimesWhenSendFailed());
        this.setMaxMessageSize(mqProducerConfig.getMaxMessageSize());
        this.setSendMsgTimeout(mqProducerConfig.getSendMsgTimeout());
        this.setNamesrvAddr(mqProducerConfig.getNamesrvAddr());
        this.setProducerGroup(mqProducerConfig.getProducerGroup());
    }

    public SendResult send(T msg) {
        Message sendMsg;
        SendResult sendResult;
        try {
            sendMsg = new Message(this.getTopic(), "tag",
                JSONObject.toJSONString(msg).getBytes(Charsets.UTF_8.displayName()));
            sendResult = this.send(sendMsg);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            log.error("发送消息异常 msg:[{}]", msg, e);
            return null;
        }
        //默认3秒超时
        log.info("消息发送响应信息:[{}]", sendResult);
        return sendResult;
    }
}