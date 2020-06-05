/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * 在这里编写类的功能描述
 *
 * @author Guimu
 * @date 2020/06/05
 */
@Slf4j
public class CustomBulkProcessorListener implements BulkProcessor.Listener {


    @Override
    public void afterBulk(long executionId, BulkRequest bulkRequest, BulkResponse bulkResponse) {
        this.logWhenSuc(bulkResponse);
    }

    @Override
    public void beforeBulk(long executionId, BulkRequest bulkRequest) {
        log.debug("begin bulk processor");
    }

    @Override
    public void afterBulk(long executionId, BulkRequest bulkRequest, Throwable throwable) {
        log.debug("after bulk when fail");
    }

    private void logWhenSuc(BulkResponse response) {
        if (response.hasFailures()) {
            log.error("failure message:{}", response.buildFailureMessage());
        }
        log.debug("consume count:{}, interval time:{}", response.getItems().length,
            response.getTook().millis());
    }
}