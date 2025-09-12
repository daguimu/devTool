/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dagm.devtool.config.ElasticSearchConfig;
import com.dagm.devtool.service.ElasticSearchStoreClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

// Note: This implementation has been updated for Elasticsearch 8.x
// Some methods may need further refinement based on your specific use cases

/**
 * @author Guimu
 * @date 2020/06/05
 */
@Slf4j
@Service
@ConditionalOnBean(ElasticSearchConfig.class)
public class ElasticSearchStoreClientImpl implements ElasticSearchStoreClient {

    @Resource
    private ElasticsearchClient elasticsearchClient;
    
    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 添加或更新数据到ES
     */
    @Override
    public void writeDataToEs(Map map, String index, String type, String id) {
        try {
            UpdateRequest<Map, Map> updateRequest = UpdateRequest.of(u -> u
                .index(index)
                .id(id)
                .doc(map)
                .upsert(map)
            );
            elasticsearchClient.update(updateRequest, Map.class);
        } catch (IOException e) {
            log.error("Failed to write data to ES", e);
        }
    }

    @Override
    public void delete(String index, String type, String id) {
        try {
            DeleteRequest deleteRequest = DeleteRequest.of(d -> d
                .index(index)
                .id(id)
            );
            elasticsearchClient.delete(deleteRequest);
        } catch (IOException e) {
            log.error("Failed to delete document from ES", e);
        }
    }

    @Override
    public void writeDataToEs(String str, String index, String type, String id) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = objectMapper.readValue(str, Map.class);
            writeDataToEs(map, index, type, id);
        } catch (IOException e) {
            log.error("Failed to write string data to ES", e);
        }
    }

    /**
     * 根据id进行批量删除 index 和type
     *
     * @author Guimu
     * @date 2019-09-04
     */
    @Override
    public void batchDelete(String index, String type, List<String> idList) throws IOException {
        List<BulkOperation> bulkOps = new ArrayList<>();
        for (String id : idList) {
            bulkOps.add(BulkOperation.of(op -> op
                .delete(d -> d.index(index).id(id))
            ));
        }
        
        BulkRequest bulkRequest = BulkRequest.of(b -> b
            .index(index)
            .operations(bulkOps)
        );
        
        elasticsearchClient.bulk(bulkRequest);
    }

    @Override
    public SearchResponse<Object> search(String index, String docType, Query query) throws IOException {
        return this.search(index, docType, query, null);
    }

    @Override  
    public SearchResponse<Object> search(String index, String docType, Query query, String searchType) throws IOException {
        // Note: In ES 8.x, SearchType and docType are no longer used in the same way
        SearchRequest searchRequest = SearchRequest.of(s -> s
            .index(index)
            .query(query)
        );
        return elasticsearchClient.search(searchRequest, Object.class);
    }
}