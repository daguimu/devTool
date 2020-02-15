/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import com.dagm.devtool.common.BaseCode;
import com.dagm.devtool.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreconditionsUtil {

    /**
     * 表达式校验
     *
     * @param expression 待校验的表达式
     * @param code 基础错误码
     */
    public static void checkArgument(boolean expression, BaseCode code) {
        if (!expression) {
            log.info("抛出业务异常,code:[{}],errMsg:[{}]", code.getCode(), code.getMsg());
            throw new CommonException(code.getCode(), String.valueOf(code.getMsg()));
        }
    }
}