/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Guimu
 * @date 2021/05/02
 */
@Getter
@Setter
public class BaseString extends BaseObject {
    private String val;

    private BaseString(String val) {
        this.val = val;
    }

    public static BaseString valueOf(String val) {
        return new BaseString(val);
    }
}