package com.dagm.devtool.enums;

import com.dagm.devtool.utils.SensitiveInfoUtils;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
@AllArgsConstructor
public enum SensitiveTypeEnum {
    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号 例：张**
     */
    CHINESE_NAME(0, (el) -> SensitiveInfoUtils.chineseName(el.toString())),
    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护 例：北京****
     */
    ADDRESS(1, (el) -> SensitiveInfoUtils.address(el.toString())),
    /**
     * 对名字长度进行判断：两个字的脱敏后面[张*];三个字的脱敏中间[张*培];四个字及以上的脱敏前两个以后的[张培**];
     */
    FULL_CHINESE_NAME(2, (el) -> SensitiveInfoUtils.fullChineseName(el.toString())),
    /**
     * [身份证号] 123****12，前面保留3位明文，后面保留2位明文
     */
    IDENTIFICATION_NUM(3, (el) -> SensitiveInfoUtils.identificationNum(el.toString())),
    /**
     * [固定电话] 后四位，其他隐藏 例：****1234
     */
    FIXED_PHONE(4, (el) -> SensitiveInfoUtils.fixedPhone(el.toString())),
    /**
     * [手机号码] 前3位，后4位，其他隐藏 例:123****1234
     */
    MOBILE_PHONE(5, (el) -> SensitiveInfoUtils.mobilePhone(el.toString())),
    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示 例:g**@163.com
     */
    EMAIL(6, (el) -> SensitiveInfoUtils.email(el.toString())),
    /**
     * [银行卡号] 前四位，后四位，其他用星号隐藏每位1个星号 例:6222600**********1234
     */
    BANK_CARD(7, (el) -> SensitiveInfoUtils.bankCard(el.toString())),
    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号 例:12********
     */
    CAPS_CODE(8, (el) -> SensitiveInfoUtils.cnapsCode(el.toString())),
    ;
    private Integer val;
    private Function<Object, String> deSensFunction;
}