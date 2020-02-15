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
 * @author Guimu
 * @date  2019/10/07
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    private String code;
    private String message;

    /**
     * @author Guimu
     * @date 2020/2/15
     */
    public CommonException() {
    }

    /**
     * @param code 异常代码
     * @param message 异常message
     * @author Guimu
     * @date 2020/2/15
     */
    public CommonException(String code, String message) {
        super("code:" + code + " message:" + message);
        this.code = code;
        this.message = message;
    }

    /**
     * @param commonCodeEnum 提示错误
     * @author Guimu
     * @date 2020/2/15
     */
    public CommonException(BaseTipCode commonCodeEnum) {
        super("code:" + commonCodeEnum.getCode() + " message:" + commonCodeEnum.getMsg());
        this.code = commonCodeEnum.getCode();
        this.message = commonCodeEnum.getMsg();
    }

}