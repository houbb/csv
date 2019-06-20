package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Float 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class FloatReadConverter implements ITypeConverter<Float> {

    @Override
    public Float convert(String value, final Class type) {
        return Float.valueOf(value);
    }

}
