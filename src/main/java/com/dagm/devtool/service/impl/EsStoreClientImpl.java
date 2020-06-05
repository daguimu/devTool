/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.impl;

import com.dagm.devtool.config.ElasticSearchConfig;
import com.dagm.devtool.service.EsStoreClient;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * @author Guimu
 * @date 2020/06/05
 */
@Slf4j
@Service
@ConditionalOnBean(ElasticSearchConfig.class)
public class EsStoreClientImpl implements EsStoreClient {

    @Resource
    private BulkProcessor bulkProcessor;

    @Resource
    private RestHighLevelClient restHighLevelClient;


    /**
     * 添加或更新数据到ES
     */
    @Override
    public void writeDataToEs(Map map, String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        updateRequest.doc(map);
        updateRequest.upsert(map, XContentType.JSON);
        bulkProcessor.add(updateRequest);
    }

    @Override
    public void delete(String index, String type, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        bulkProcessor.add(deleteRequest);
    }

    @Override
    public void writeDataToEs(String str, String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        updateRequest.doc(str, XContentType.JSON);
        updateRequest.upsert(str, XContentType.JSON);
        bulkProcessor.add(updateRequest);
    }

    /**
     * 根据id进行批量删除 index 和type
     *
     * @author Guimu
     * @date 2019-09-04
     */
    @Override
    public void batchDelete(String index, String type, List<String> idList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        idList.forEach(el -> bulkRequest.add(new DeleteRequest(index, type, el)));
        restHighLevelClient.bulk(bulkRequest);
    }

    @Override
    public SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder) throws IOException {
        return this.search(index, docType, searchRequestBuilder, SearchType.DFS_QUERY_THEN_FETCH);
    }

    @Override
    public SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder, SearchType searchType) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index)
            .searchType(searchType)
            .types(docType)
            .source(searchRequestBuilder);
        return this.restHighLevelClient.search(searchRequest);
    }
}