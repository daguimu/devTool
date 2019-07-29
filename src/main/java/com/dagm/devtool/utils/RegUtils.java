/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串正则相关工具包
 *
 * @author: Guimu
 * @created: 2019/07/29
 */
public class RegUtils {

    /**
     * @Description: 判断字符串是否符合该正则模式, 符合返回true，else 返回false
     * @Param: [reg, source]
     * @Return: boolean
     * @Author: Guimu
     * @Date: 2018/8/21  下午3:11
     */
    public static boolean test(String reg, String source) {
        return Pattern.matches(reg, source);
    }

    /**
     * @Description: 获取source源数据中符合指定模式的内容，以List的方式返回
     * @Param: [reg, source]
     * @Return: java.util.List<java.lang.String>
     * @Author: Guimu
     * @Date: 2018/8/21  下午3:15
     */
    public static List<String> getPatternContent(String reg, String source) {
        List<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }
}