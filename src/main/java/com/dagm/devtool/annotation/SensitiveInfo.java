/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.annotation;

import com.dagm.devtool.enums.SensitiveTypeEnum;
import com.dagm.devtool.serializer.SensitiveInfoSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记:字段使用何种策略来脱敏 只能用于 Long和String 类型
 *
 * @author Guimu
 * @date 2019/10/14
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

    SensitiveTypeEnum value();
}

