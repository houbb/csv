package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Boolean 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class BooleanReadConverter implements ITypeConverter<Boolean> {

    @Override
    public Boolean convert(String value, Class type) {
        return Boolean.valueOf(value);
    }

}
