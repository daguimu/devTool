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
 * @ author: Guimu
 */
@UtilityClass
public class DateTimeUtil {

    /**
     * 检查 dateTime 是否符合 formatEnum 所对应的格式;例如 20:19:62 为false，09-31 为true
     *
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
     * @return String
     */
    public String getCurrentTimeAsStr(DateFormatEnum formatEnum) {
        return dateToLocalDateTime(LocalDateTime.now(), formatEnum);
    }


    /**
     * 将localDateTime 转换成指定的时间格式
     */
    public String dateToLocalDateTime(LocalDateTime localDateTime, DateFormatEnum formatEnum) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatEnum.getFormat());
        return df.format(localDateTime);
    }

    /**
     * 计算获取两个时间字符串的时间差
     */
    public Duration dateDiff(String startTime, String endTime, DateFormatEnum startFormatEnum,
        DateFormatEnum endFormatEnum) {
        LocalDateTime startLocalTime = strToLocalDateTime(startTime, startFormatEnum);
        LocalDateTime endLocalTime = strToLocalDateTime(endTime, endFormatEnum);
        return Duration.between(startLocalTime, endLocalTime);
    }

    /**
     * 计算获取两个时间字符串的时间差
     */
    public Duration dateDiff(String startTime, String endTime, DateFormatEnum formatEnum) {
        LocalDateTime startLocalTime = strToLocalDateTime(startTime, formatEnum);
        LocalDateTime endLocalTime = strToLocalDateTime(endTime, formatEnum);
        return Duration.between(startLocalTime, endLocalTime);
    }
}