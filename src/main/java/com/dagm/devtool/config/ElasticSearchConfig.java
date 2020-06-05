/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.config;

import com.dagm.devtool.properties.ElasticProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guimu
 * @date 2020/06/05
 */
@Configuration
@EnableConfigurationProperties(ElasticProperties.class)
@ConditionalOnProperty(name = "spring.elastic.enable",havingValue = "true")
@Slf4j
public class ElasticSearchConfig {

    @Autowired
    private ElasticProperties elasticProperties;

    /**
     * 连接超时时间
     */
    private static int CONNECT_TIME_OUT = 1000;

    /**
     * 连接超时时间
     */
    private static int SOCKET_TIME_OUT = 30000;

    /**
     * 获取连接的超时时间
     */
    private static int CONNECTION_REQUEST_TIMEOUT = 500;

    /**
     * 最大连接数
     */
    private static int MAX_CONNECT_NUM = 100;

    /**
     * 最大路由连接数
     */
    private static int MAX_CONNECT_PER_ROUTE = 100;

    /**
     * 最大重试超时时间
     */
    private static int MAX_RETRY_TIME_OUT = 30000;

    /**
     * 协议类型
     */
    private static String SCHEME = "http";


    private static final int DEFAULT_BULK_ACTIONS = 200;

    private static final int DEFAULT_BULK_SIZE = 5;

    private static final int DEFAULT_FLUSH_INTERVAL_IN_SECONDS = 5;

    private static final int DEFAULT_CONCURRENT_REQUESTS = 4;

    private static final int DEFAULT_BACKOFF_POLICY_IN_MILLIS = 100;

    private static final int DEFAULT_BACKOFF_POLICY_MAX_NUMBER_OF_RETRIES = 3;


    @Bean
    public RestHighLevelClient buildClient() {
        RestHighLevelClient restHighLevelClient = null;
        try {

            RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(
                    elasticProperties.getHost(),
                    Integer.parseInt(elasticProperties.getPort()),
                    SCHEME));
            restClientBuilder.setRequestConfigCallback(
                (builder) -> builder.setConnectTimeout(CONNECT_TIME_OUT)
                    .setSocketTimeout(SOCKET_TIME_OUT)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT))
                .setMaxRetryTimeoutMillis(MAX_RETRY_TIME_OUT);
            restClientBuilder
                .setHttpClientConfigCallback((builder) -> builder.setMaxConnTotal(MAX_CONNECT_NUM)
                    .setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE));
            restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        } catch (Exception e) {
            log.error("buildClient error", e);
        }
        return restHighLevelClient;
    }

    @Bean(destroyMethod = "close")
    public BulkProcessor bulkProcessorForData(RestHighLevelClient restHighLevelClient) {
        BulkProcessor.Listener listener = new CustomBulkProcessorListener();
        return this.build(listener, restHighLevelClient);
    }

    private BulkProcessor build(BulkProcessor.Listener listener,
        RestHighLevelClient restHighLevelClient) {
        TimeValue initialDelay = TimeValue.timeValueMillis(DEFAULT_BACKOFF_POLICY_IN_MILLIS);
        return BulkProcessor.builder(restHighLevelClient::bulkAsync, listener)
            .setBulkActions(DEFAULT_BULK_ACTIONS)
            .setBulkSize(new ByteSizeValue(DEFAULT_BULK_SIZE, ByteSizeUnit.MB))
            .setFlushInterval(TimeValue.timeValueSeconds(DEFAULT_FLUSH_INTERVAL_IN_SECONDS))
            .setConcurrentRequests(DEFAULT_CONCURRENT_REQUESTS)
            .setBackoffPolicy(BackoffPolicy
                .exponentialBackoff(initialDelay, DEFAULT_BACKOFF_POLICY_MAX_NUMBER_OF_RETRIES))
            .build();
    }
}





