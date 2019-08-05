/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import static com.dagm.devtool.enums.DateFormatEnum.GENERAL_TIME_FORMAT;

import com.dagm.devtool.enums.DateFormatEnum;
import org.junit.Test;

/**
 * 时间工具类
 *
 * @author: Guimu
 * @created: 2019/08/02 * @since 1.8
 */
public class DateTimeUtil {

    public boolean checkDateFormat(String dateTime, DateFormatEnum formatEnum) {
        String format = formatEnum.getFormat();
        String reg = format.replaceAll("([ymhdHMs]{2,})",
            "\\d{" + ("$0".length()) + "}");
        System.out.println(dateTime);
        System.out.println(reg);
        return RegUtil.test(reg, dateTime);
    }

    @Test
    public void test() {
        System.out.println(this.checkDateFormat("2019-09-08 18:32:21", GENERAL_TIME_FORMAT));
    }
}