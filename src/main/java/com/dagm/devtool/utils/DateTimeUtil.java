/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import static com.dagm.devtool.common.BaseErrorCode.OUTTER_PARAM_ERROR;

import com.dagm.devtool.enums.DateFormatEnum;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author: Guimu
 * @created: 2019/08/02 * @since 1.8
 */

//@UtilityClass
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
     * 获取指定格式的时间
     *
     * @return String
     */
    public String getCurrentTimeAsStr(DateFormatEnum formatEnum) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatEnum.getFormat());
        return df.format(LocalDateTime.now());
    }
}