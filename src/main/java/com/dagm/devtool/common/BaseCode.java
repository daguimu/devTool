/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.common;

/**
 * @author: Guimu
 */
public interface BaseCode {

    /**
     * 获取code 码
     *
     * @return String
     */
    String getCode();

    /**
     * 获取code错误对应的描述信息
     *
     * @return String
     */
    String getMsg();
}
