package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Integer 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class IntegerReadConverter implements ITypeConverter<Integer> {

    @Override
    public Integer convert(String value, final Class type) {
        return Integer.valueOf(value);
    }

}
