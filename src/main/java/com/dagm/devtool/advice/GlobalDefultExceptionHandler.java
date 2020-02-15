/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.advice;

import com.dagm.devtool.exceptions.CommonException;
import com.dagm.devtool.res.BaseResult;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult<String> defultExcepitonHandler(HttpServletRequest request, Exception e) {

        BaseResult<String> baseResult = BaseResult.generateFailureResult(e.getMessage());
        if (e instanceof CommonException) {
            baseResult.setCode(((CommonException) e).getCode());
            log.info("", e);
        } else {
            log.error("", e);
        }
        return baseResult;
    }
}