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
 */
public class RegUtils {

    /**
     * @return boolean
     * @author dagm
     *
     * @since 1.8
     */
    public static boolean test(String reg, String source) {
        return Pattern.matches(reg, source);
    }

    /**
     * @return List
     * @author dagm
     * @since 1.8
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