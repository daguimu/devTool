/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Guimu
 * @date 2020/01/09
 */
@Getter
@Setter
public class AccessModel extends BaseObject {

    private String cacheKey;
    private String accessKeyId;
    private String accessKeySecret;
}