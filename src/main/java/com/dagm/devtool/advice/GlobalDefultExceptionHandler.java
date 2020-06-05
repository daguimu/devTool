/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.advice;

import com.dagm.devtool.exceptions.CommonException;
import com.dagm.devtool.res.BaseResult;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Slf4j
@RestControllerAdvice
public class GlobalDefultExceptionHandler {

    /**
     * 声明要捕获的异常
     *
     * @param request : req
     * @param e: e
     * @return com.dagm.devtool.res.BaseResult java.lang.String
     * @author Guimu
     * @date 2020/4/10
     */
    @ExceptionHandler(Exception.class)
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

    @ExceptionHandler(BindException.class)
    public Object validExceptionHandler(BindException e) {
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        return bindingResult(errorList);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        return bindingResult(errorList);
    }

    private Object bindingResult(List<ObjectError> errorList) {
        StringBuilder errorBuilder = new StringBuilder();
        for (ObjectError fieldError : errorList) {
            errorBuilder.append(fieldError.getDefaultMessage())
                .append("。");
        }
        String msg = errorBuilder.toString();
        log.info(msg);
        return BaseResult.generateFailureResult(msg);
    }
}