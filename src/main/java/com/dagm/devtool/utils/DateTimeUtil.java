/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import static com.dagm.devtool.common.BaseErrorCode.OUTTER_PARAM_ERROR;

import com.dagm.devtool.enums.DateFormatEnum;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author: Guimu
 */
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
    public static boolean checkDateFormat(String dateTime, DateFormatEnum formatEnum) {
        return RegUtil.test(formatEnum.getReg(), dateTime);
    }

    /**
     * 毫秒级时间戳转字符串
     *
     * @param millTimeStamp 毫秒级时间戳
     * @param formatEnum 字符串日期格式
     * @author: Guimu
     */
    public static String millTimeStampToStr(long millTimeStamp, DateFormatEnum formatEnum) {
        Date date = new Date(millTimeStamp);
        return dateToStr(date, formatEnum);
    }

    /**
     * 将时间字符串转换成对应的LocalDateTime
     *
     * @param formatEnum 期望时间数据格式
     * @param timeStr 原时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String timeStr, DateFormatEnum formatEnum) {
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
    public static String getCurrentTimeAsStr(DateFormatEnum formatEnum) {
        return dateToLocalDateTime(LocalDateTime.now(), formatEnum);
    }


    /**
     * 将localDateTime 转换成指定的时间格式
     *
     * @param formatEnum 期望时间数据格式
     * @param localDateTime 源数据时间
     * @return 时间字符串
     */
    public static String dateToLocalDateTime(LocalDateTime localDateTime,
        DateFormatEnum formatEnum) {
        return DateTimeFormatter.ofPattern(formatEnum.getFormat()).format(localDateTime);
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
    public static Duration dateDiff(String startTime, String endTime,
        DateFormatEnum startFormatEnum, DateFormatEnum endFormatEnum) {
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
    public static Duration dateDiff(String startTime, String endTime, DateFormatEnum formatEnum) {
        LocalDateTime startLocalTime = strToLocalDateTime(startTime, formatEnum);
        LocalDateTime endLocalTime = strToLocalDateTime(endTime, formatEnum);
        return Duration.between(startLocalTime, endLocalTime);
    }

    /**
     * 将时间字符串timeStr 转换成对应的Date 对象
     *
     * @param timeStr 字符串时间源数据
     * @param formatEnum 字符串时间的格式
     * @return Date 时间对象
     */
    public static Date timeStrToDate(String timeStr, DateFormatEnum formatEnum) {
        LocalDateTime localDateTime = DateTimeUtil.strToLocalDateTime(timeStr, formatEnum);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 将localDateTime 对象转为Date
     *
     * @param localDateTime 时间数据源
     * @return Date 时间对象
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将时间Date 转换成对应的时间字符串对
     *
     * @param date 时间对象源数据
     * @param formatEnum 目标字符串时间的格式
     * @return Date 时间对象
     */
    public static String dateToStr(Date date, DateFormatEnum formatEnum) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatEnum.getFormat());
        return sdf.format(date);
    }

    /**
     * 将指定时间戳 转换成对应的时间字符串对
     *
     * @param time 时间对象源数据
     * @param formatEnum 目标字符串时间的格式
     * @return Date 时间对象
     */
    public static String timeStampToStr(Long time, DateFormatEnum formatEnum) {
        return new SimpleDateFormat(formatEnum.getFormat()).format(time);
    }

    /**
     * 将对象Date 转为localDateTime
     *
     * @param date 时间数据源
     * @return LocalDateTime 时间对象
     */
    public static LocalDateTime localDateTimeToDate(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * 获取当前时间戳毫秒
     *
     * @return Long 时间对象
     */
    public static Long currentTimeStamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}