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
 */
@AllArgsConstructor
@Getter
public enum BaseTipCode implements BaseCode {
    /**
     * 操作成功
     */
    SUCCESS("0000", "操作成功"),
    /**
     * 操作失败
     */
    FAILURE("9999", "操作失败"),
    ;
    private String code;
    private String msg;
}