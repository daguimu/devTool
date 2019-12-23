/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间格式枚举类
 *
 * @author: Guimu
 */

@Getter
@AllArgsConstructor
public enum DateFormatEnum {
    /**
     * 大众版时间格式
     */
    GENERAL_TIME_FORMAT("大众版时间格式", "yyyy-MM-dd HH:mm:ss",
        "(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])"),
    /**
     * 大众版时间格式 带毫秒版
     */
    GENERAL_TIME_FORMAT_MILL("大众版时间格式", "yyyy-MM-dd HH:mm:ss.SSS",
        "(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])([0-9]{3})"),

    /**
     * 数字版时间格式
     */
    NUMBER_TIME_FORMAT("数字版时间格式", "yyyyMMddHHmmss",
        "(\\d{4})(0[1-9]|1[0-2])(0[1-9]|[12]\\d{1}|3[01])(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])"),
    /**
     * 斜杠版时间格式
     */
    SLASH_TIME_FORMAT("斜杠版时间格式", "yyyy/MM/dd HH:mm:ss",
        "(\\d{4})/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])"),
    /**
     * 大众版时间格式没有秒
     */
    GENERAL_TIME_FORMAT_NO_SEC("大众版时间格式没有秒", "yyyy-MM-dd HH:mm",
        "(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])"),
    /**
     * 年－月－日 时间格式
     */
    FULL_DATE_NO_TIME("年－月－日", "yyyy-MM-dd", "(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d{1}|3[01])"),
    /**
     * 月－日    时间格式
     */
    MONTH_DAY("月－日", "MM-dd", "(0[1-9]|1[0-2])-(0[1-9]|[12]\\d{1}|3[01])"),
    /**
     * 时：分钟：秒 时间格式
     */
    JUST_TIME("时：分钟：秒", "HH:mm:ss", "(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])"),
    /**
     * 年-月 时间格式
     */
    YEAR_MONTH("年-月", "yyyy-MM", "(\\d{4})-(0[1-9]|1[0-2])");

    private String desc;
    private String format;
    private String reg;
}
