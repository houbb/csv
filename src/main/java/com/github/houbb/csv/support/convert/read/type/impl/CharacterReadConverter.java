package com.github.houbb.csv.support.convert.read.type.impl;

import com.github.houbb.csv.support.convert.read.type.ITypeConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Character 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class CharacterReadConverter implements ITypeConverter<Character> {

    @Override
    public Character convert(String value, final Class type) {
        return value.charAt(0);
    }

}
