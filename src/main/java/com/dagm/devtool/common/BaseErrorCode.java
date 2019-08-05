/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Guimu
 * @created: 2019/08/05
 */
@AllArgsConstructor
@Getter
public enum BaseErrorCode implements BaseCode {
    /**
     * 内部参数错误
     */
    INNER_PARAM_ERROR("9001", "内部参数错误"),
    /**
     * 外部部参数错误
     */
    OUTTER_PARAM_ERROR("9002", "外部部参数错误"),
    /**
     * 系统内部异常错误
     */
    SYSTEM_ERROR("9003", "系统内部异常错误"),
    /**
     * 未知错误
     */
    UNKNOW_ERROR("9004", "未知错误"),
    ;

    private String code;
    private String msg;

}