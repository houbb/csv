package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * 字符串读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class StringReadConverter implements ITypeConverter<String> {

    @Override
    public String convert(String value, final Class type) {
        return value;
    }

}
