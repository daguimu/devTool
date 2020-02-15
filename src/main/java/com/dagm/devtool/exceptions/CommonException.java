/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.exceptions;

import com.dagm.devtool.common.BaseTipCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Guimu
 * @created: 2019/10/07
 */
@Getter
@Setter
public class CommonException extends RuntimeException{
    private String code;
    private String message;

    public CommonException() {
    }

    public CommonException(String code, String message) {
        super("code:" + code + " message:" + message);
        this.code = code;
        this.message = message;
    }

    public CommonException(BaseTipCode commonCodeEnum) {
        super("code:" + commonCodeEnum.getCode() + " message:" + commonCodeEnum.getMsg());
        this.code = commonCodeEnum.getCode();
        this.message = commonCodeEnum.getMsg();
    }

}