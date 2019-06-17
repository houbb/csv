package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

import java.lang.reflect.Field;

/**
 * Byte 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ByteReadConverter implements ITypeConverter<Byte> {

    @Override
    public Byte convert(String value, final Class type) {
        return Byte.valueOf(value);
    }

}
