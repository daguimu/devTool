/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;

/**
 * @author Guimu
 * @date 2019/12/10
 */
@Slf4j
public abstract class BaseProcessor<T> implements MessageListenerConcurrently {

    /**
     * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息 不要抛异常，如果没有return CONSUME_SUCCESS
     * ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     *
     * @param list 接收到的消息
     * @author Guimu
     * @date 2020/6/10
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
        ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        MessageExt messageExt = list.get(0);
        //消息已经重试了3次，如果不需要再次消费，则返回成功
        int reconsume = messageExt.getReconsumeTimes();
        if (reconsume == 3) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        String messageBody = null;
        try {
            messageBody = new String(messageExt.getBody(), Charsets.UTF_8.displayName());
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
            log.info("BaseProcessor received a message body:{}", messageBody);
            this.consume(JSON.parseObject(messageBody, clazz));
        } catch (UnsupportedEncodingException e) {
            log.error("消费消息失败,msgId={},messageBody={}", messageExt.getMsgId(), messageBody, e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        log.info("消费消息成功,msgId={},messageBody={}", messageExt.getMsgId(), messageBody);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 对消息进行消费
     *
     * @param msg 即将消费的消息
     * @author Guimu
     * @date 2020/6/10
     */
    public abstract void consume(T msg);
}