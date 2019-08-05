/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import static com.dagm.devtool.common.BaseErrorCode.OUTTER_PARAM_ERROR;

import com.dagm.devtool.enums.DateFormatEnum;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

/**
 * 时间工具类
 *
 * @author: Guimu
 */
@UtilityClass
public class DateTimeUtil {

    /**
     * 检查 dateTime 是否符合 formatEnum 所对应的格式;例如 20:19:62 为false，09-31 为true
     *
     * @param dateTime 源数据时间
     * @param formatEnum 期望时间数据格式
     * @return boolean
     * @author dagm
     * @since 1.8
     */
    public boolean checkDateFormat(String dateTime, DateFormatEnum formatEnum) {
        return RegUtil.test(formatEnum.getReg(), dateTime);
    }

    /**
     * 将时间字符串转换成对应的LocalDateTime
     *
     * @param formatEnum 期望时间数据格式
     * @param timeStr 原时间字符串
     * @return LocalDateTime
     */
    public LocalDateTime strToLocalDateTime(String timeStr, DateFormatEnum formatEnum) {
        PreconditionsUtil.checkArgument(checkDateFormat(timeStr, formatEnum), OUTTER_PARAM_ERROR);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatEnum.getFormat());
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * 以指定格式,获取当前时间
     *
     * @param formatEnum 时间格式
     * @return String
     */
    public String getCurrentTimeAsStr(DateFormatEnum formatEnum) {
        return dateToLocalDateTime(LocalDateTime.now(), formatEnum);
    }


    /**
     * 将localDateTime 转换成指定的时间格式
     *
     * @param formatEnum 期望时间数据格式
     * @param localDateTime 源数据时间
     * @return 时间字符串
     */
    public String dateToLocalDateTime(LocalDateTime localDateTime, DateFormatEnum formatEnum) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatEnum.getFormat());
        return df.format(localDateTime);
    }

    /**
     * 计算获取两个时间字符串的时间差
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param startFormatEnum 开始时间格式
     * @param endFormatEnum 结束时间格式
     * @return Duration 时间差对象
     */
    public Duration dateDiff(String startTime, String endTime, DateFormatEnum startFormatEnum,
        DateFormatEnum endFormatEnum) {
        LocalDateTime startLocalTime = strToLocalDateTime(startTime, startFormatEnum);
        LocalDateTime endLocalTime = strToLocalDateTime(endTime, endFormatEnum);
        return Duration.between(startLocalTime, endLocalTime);
    }

    /**
     * 计算获取两个时间字符串的时间差
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param formatEnum 时间格式
     * @return Duration 时间差对象
     */
    public Duration dateDiff(String startTime, String endTime, DateFormatEnum formatEnum) {
        LocalDateTime startLocalTime = strToLocalDateTime(startTime, formatEnum);
        LocalDateTime endLocalTime = strToLocalDateTime(endTime, formatEnum);
        return Duration.between(startLocalTime, endLocalTime);
    }
}