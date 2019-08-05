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
 * @created: 2019/08/05
 */
@AllArgsConstructor
@Getter
public enum DateFormatEnum {
    /**
     * 大众版时间格式
     */
    GENERAL_TIME_FORMAT("大众版时间格式", "yyyy-MM-dd HH:mm:ss"),

    /**
     * 数字版时间格式
     */
    NUMBER_TIME_FORMAT("数字版时间格式", "yyyyMMddHHmmss"),
    /**
     * 斜杠版时间格式
     */
    SLASH_TIME_FORMAT("斜杠版时间格式", "yyyy/MM/dd HH:mm:ss"),
    /**
     * 大众版时间格式没有秒
     */
    GENERAL_TIME_FORMAT_NO_SEC("大众版时间格式没有秒", "yyyy-MM-dd HH:mm"),
    /**
     * 年－月－日 时间格式
     */
    FULL_DATE_NO_TIME("年－月－日", "yyyy-MM-dd"),
    /**
     * 月－日    时间格式
     */
    MONTH_DAY("月－日", "MM-dd"),
    /**
     * 时：分钟：秒 时间格式
     */
    JUST_TIME("时：分钟：秒", "HH:mm:ss"),
    /**
     * 年-月 时间格式
     */
    YEAR_MONTH("年-月", "yyyy-MM");

    private String desc;
    private String format;
}
