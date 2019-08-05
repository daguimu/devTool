/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import com.dagm.devtool.common.BaseCode;
import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;

/**
 * @author: Guimu
 */
@UtilityClass
public class PreconditionsUtil {

    /**
     * 表达式校验
     *
     * @param expression 待校验的表达式
     * @param code 基础错误码
     */
    public void checkArgument(boolean expression, BaseCode code) {
        Preconditions.checkArgument(expression,
            String.format("[错误码: %s, 错误信息: %s]", code.getCode(), code.getMsg()));
    }
}