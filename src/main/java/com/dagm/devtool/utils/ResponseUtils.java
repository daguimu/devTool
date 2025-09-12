/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import com.alibaba.fastjson2.JSONObject;
import com.dagm.devtool.res.BaseResult;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Guimu
 * @date 2020/02/15
 */
public class ResponseUtils {

    public static void littleAuthor(HttpServletResponse response, String errorMsg)
        throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        BaseResult<String> bwJsonResult = BaseResult.generateFailureResult(errorMsg);
        writer.print(JSONObject.toJSONString(bwJsonResult));
        response.flushBuffer();
        writer.close();
    }

}