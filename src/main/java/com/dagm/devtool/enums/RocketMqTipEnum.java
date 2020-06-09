/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.enums;

import com.dagm.devtool.common.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Getter
@AllArgsConstructor
public enum RocketMqTipEnum implements BaseCode {
    /**
     * groupName is blank
     */
    GROUP_NAME_IS_BLANK("9119", "groupName is blank"),
    /**
     * nameServerAddr is blank"),
     */
    NAME_ADDR_IS_BLANK("9120", "nameServerAddr is blank"),
    /**
     * topics is null
     */
    TOPICS_IS_NULL("9120", "topics is null !!!"),
    ;
    private String code;

    private String msg;
}