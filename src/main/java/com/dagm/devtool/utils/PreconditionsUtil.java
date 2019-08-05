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
 * @created: 2019/08/05
 */
@UtilityClass
public class PreconditionsUtil {

    /**
     * 表达式校验
     */
    public void checkArgument(boolean expression, BaseCode code) {
        Preconditions.checkArgument(expression,
            String.format("[错误码: %s, 错误信息: %s]", code.getCode(), code.getMsg()));
    }
}