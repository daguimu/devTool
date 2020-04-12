/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dagm.devtool.res.BaseResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

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
        writer.print(JSONObject.toJSONString(bwJsonResult, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat));
        response.flushBuffer();
        writer.close();
    }

}