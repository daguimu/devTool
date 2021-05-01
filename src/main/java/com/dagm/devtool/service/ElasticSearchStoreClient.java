/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Guimu
 * @date 2020/06/05
 */
public interface ElasticSearchStoreClient {

    /**
     * 添加或更新数据到ES
     *
     * @param map : 数据map
     * @param index : 索引
     * @param type : 类型
     * @param id : 数据id
     * @author Guimu
     * @date 2020/6/9
     */
    void writeDataToEs(Map map, String index, String type, String id);

    /**
     * 删除数据
     *
     * @param index : 索引
     * @param type : 类型
     * @param id id
     * @author Guimu
     * @date 2020/6/9
     */
    void delete(String index, String type, String id);

    /**
     * @param str : json 串
     * @param index : 索引
     * @param id id
     * @param type : 类型
     * @author Guimu
     * @date 2020/6/9
     */
    void writeDataToEs(String str, String index, String type, String id);

    /**
     * 根据id进行批量删除 index 和type
     *
     * @param index : 索引
     * @param type : 类型
     * @param idList id 集合
     * @throws java.io.IOException io 异常
     * @author Guimu
     * @date 2019-09-04
     */
    void batchDelete(String index, String type, List<String> idList) throws IOException;

    /**
     * @param index : 索引
     * @param docType : 类型
     * @param searchRequestBuilder builder
     * @throws java.io.IOException io 异常
     * @return SearchResponse 搜索结果响应
     * @author Guimu
     * @date 2020/6/9
     */
    SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder) throws IOException;

    /**
     * @param index : 索引
     * @param docType : 类型
     * @param searchType : 搜索类型
     * @param searchRequestBuilder : builder
     * @throws java.io.IOException io 异常
     * @return SearchResponse 搜索结果响应
     * @author Guimu
     * @date 2020/6/9
     */
    SearchResponse search(String index, String docType,
        SearchSourceBuilder searchRequestBuilder, SearchType searchType) throws IOException;
}