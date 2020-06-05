/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author Guimu
 * @date 2020/06/05
 */
public interface EsStoreClient {

    /**
     * 添加或更新数据到ES
     */
    void writeDataToEs(Map map, String index, String type, String id);

    void delete(String index, String type, String id);

    void writeDataToEs(String str, String index, String type, String id);

    /**
     * 根据id进行批量删除 index 和type
     *
     * @author Guimu
     * @date 2019-09-04
     */
    void batchDelete(String index, String type, List<String> idList) throws IOException;

    SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder) throws IOException;

    SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder, SearchType searchType) throws IOException;
}