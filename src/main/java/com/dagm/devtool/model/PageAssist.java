/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 分页参数
 *
 * @author: Guimu
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
public class PageAssist {

    private Long start;
    private Long end;
    private Boolean valid;
}