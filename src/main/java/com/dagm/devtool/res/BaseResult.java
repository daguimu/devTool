/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.res;


import static com.dagm.devtool.common.BaseTipCode.FAILURE;
import static com.dagm.devtool.common.BaseTipCode.SUCCESS;

import com.dagm.devtool.common.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: Guimu
 * @created: 2019/10/07
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseResult<T> {

    private Boolean success;
    private String code;
    private String msg;
    private T data;


    /**
     * 默认成功的 BaseResult
     * @return com.dagm.devtool.res.BaseResult<java.lang.String>
     * @author Guimu
     * @date 2020/1/9
     */
    public static BaseResult<String> generateSuccessResult() {
        return new BaseResult<String>().setSuccess(true).setCode(SUCCESS.getCode())
            .setMsg(SUCCESS.getMsg());
    }

    /**
     * 根据BaseCode 构建对应的BaseResult
     *
     * @return com.dagm.devtool.res.BaseResult<java.lang.String>
     * @author Guimu 提示信息
     * @date 2020/1/9
     */
    public static BaseResult<String> generateFailureResult(BaseCode baseCode) {
        return new BaseResult<String>().setSuccess(false).setCode(FAILURE.getCode())
            .setMsg(baseCode.getMsg());
    }

    /**
     * 根据errorMsg 构建对应的 BaseResult
     *
     * @param errorMsg 错误信息
     * @return com.dagm.devtool.res.BaseResult<java.lang.String>
     * @author Guimu
     * @date 2020/1/9
     */
    public static BaseResult<String> generateFailureResult(String errorMsg) {
        return new BaseResult<String>().setSuccess(false).setCode(FAILURE.getCode())
            .setMsg(errorMsg);
    }

    /**
     * 根据data构建对应的 BaseResult
     *
     * @return com.dagm.devtool.res.BaseResult<T>
     * @author Guimu
     * @date 2020/1/9
     */
    public static <T> BaseResult<T> generateSuccessResult(T data) {
        return new BaseResult<T>().setSuccess(true).setCode(SUCCESS.getCode())
            .setMsg(SUCCESS.getMsg()).setData(data);
    }
}