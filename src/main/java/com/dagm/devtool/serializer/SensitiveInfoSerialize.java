/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.serializer;

import com.dagm.devtool.annotation.SensitiveInfo;
import com.dagm.devtool.enums.SensitiveTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: Guimu
 * @created: 2019/10/14
 */
public class SensitiveInfoSerialize extends JsonSerializer<Object> implements
    ContextualSerializer {

    private SensitiveTypeEnum type;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(final SensitiveTypeEnum type) {
        this.type = type;
    }

    @Override
    public void serialize(final Object s, final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(this.type.getDeSensFunction().apply(s));
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
        final BeanProperty beanProperty) throws JsonMappingException {
        // 为空直接跳过
        if (beanProperty != null) {
            // 非 String、Long 类直接跳过
            if (Objects
                .equals(beanProperty.getType().getRawClass(), String.class) || Objects
                .equals(beanProperty.getType().getRawClass(), Long.class)) {
                SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
                }
                if (sensitiveInfo != null) {
                    // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                    return new SensitiveInfoSerialize(sensitiveInfo.value());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}