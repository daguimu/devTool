package com.dagm.devtool.utils;

import org.apache.commons.lang3.StringUtils;

public class SensitiveInfoUtils {

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号 例：张**
     * @return String
     * @param fullName 原中文姓名
     * @author Guimu
     * @date 2019/12/24
     */
    public static String chineseName(String fullName) {
        String result = "";
        if (StringUtils.isNotBlank(fullName)) {
            String name = StringUtils.left(fullName, 1);
            result = StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return result;
    }

    /**
     * 对名字长度进行判断：两个字的脱敏后面[张*];三个字的脱敏中间[张*培];四个字及以上的脱敏前两个以后的[张培**];
     * @return String
     * @param fullName 原中文姓名
     * @author Guimu
     * @date 2019/12/24
     */
    public static String fullChineseName(String fullName) {
        String result = "";
        if (StringUtils.isNotBlank(fullName)) {
            //对名字长度进行判断：两个字的脱敏后面;三个字的脱敏中间;四个字及以上的脱敏前两个以后的;
            int nameLength = 0;
            nameLength = StringUtils.length(fullName);
            String tempName = "";
            String tempName2 = "";
            if (nameLength != 0) {
                if (nameLength == 2) {
                    tempName = StringUtils.left(fullName, 1);
                    result = StringUtils.rightPad(tempName, StringUtils.length(fullName), "*");
                } else if (nameLength == 3) {
                    tempName = StringUtils.left(fullName, 1);
                    tempName2 = StringUtils.right(fullName, 1);
                    result = tempName + "*" + tempName2;
                } else {
                    if (nameLength > 3) {
                        tempName = StringUtils.left(fullName, 2);
                        result = StringUtils.rightPad(tempName, StringUtils.length(fullName), "*");
                    }
                }
            }
        }
        return result;
    }

    /**
     * [身份证号] 123****12，前面保留3位明文，后面保留2位明文
     *
     * @return String
     * @param idCard 原身份证号
     * @author Guimu
     * @date  2019/12/24
     */
    public static String identificationNum(String idCard) {
        String result = "";
        if (StringUtils.isNotBlank(idCard)) {
            result = StringUtils.left(idCard, 3).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(idCard, 2), StringUtils.length(idCard), "*"),
                "***"));
        }
        return result;
    }

    /**
     * [固定电话] 后四位，其他隐藏 例：****1234
     * @param phone 原固定电话
     * @author Guimu
     * @date 2019/12/24
     */
    public static String fixedPhone(String phone) {
        String result = "";
        if (StringUtils.isNotBlank(phone)) {
            result = StringUtils.leftPad(StringUtils.right(phone, 4), StringUtils.length(phone), "*");
        }
        return result;
    }

    /**
     * [手机号码] 前3位，后4位，其他隐藏 例:123****1234
     * @param phone 原手机号码
     * @return String
     * @author Guimu
     * @date 2019/12/24
     */
    public static String mobilePhone(String phone) {
        String result = "";
        if (StringUtils.isNotBlank(phone)) {
            result = StringUtils.left(phone, 3).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(phone, 4), StringUtils.length(phone), "*"),
                "***"));
        }
        return result;
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护 例：北京****
     * @return String
     * @param address 原地址
     * @author Guimu
     * @date 2019/12/24
     */
    public static String address(String address) {
        String result = "";
        if (StringUtils.isNotBlank(address)) {
            int length = StringUtils.length(address);
            result = StringUtils
                .rightPad(StringUtils.left(address, 3), length, "*");
        }
        return result;
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示 例:g**@163.com
     * @param email 原电子邮箱
     * @return String
     * @author Guimu
     * @date 2019/12/24
     */
    public static String email(String email) {
        String result = "";
        if (StringUtils.isNotBlank(email)) {
            int index = StringUtils.indexOf(email, "@");
            if (index <= 1) {
                result = email;
            } else {
                result = StringUtils.rightPad(StringUtils.left(email, 1), index, "*")
                    .concat(StringUtils.mid(email, index, StringUtils.length(email)));
            }
        }
        return result;
    }

    /**
     * [银行卡号] 前四位，后四位，其他用星号隐藏每位1个星号 例:6222600**********1234
     * @return String
     * @param cardNum 原银行卡号
     * @author Guimu
     * @date 2019/12/24
     */
    public static String bankCard(String cardNum) {
        String result = "";
        if (StringUtils.isNotBlank(cardNum)) {
            result = StringUtils.left(cardNum, 4).concat(StringUtils.removeStart(StringUtils
                    .leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"),
                "******"));
        }
        return result;
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号 例:12********
     * @param code 原公司开户银行联号
     * @return String
     * @author Guimu
     * @date 2019/12/24
     */
    public static String cnapsCode(String code) {
        String result = "";
        if (StringUtils.isNotBlank(code)) {
            result = StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
        }
        return result;
    }
}