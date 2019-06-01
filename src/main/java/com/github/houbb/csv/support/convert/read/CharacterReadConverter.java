package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Character 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class CharacterReadConverter implements IReadConverter<Character> {

    @Override
    public Character convert(String value, Class fieldType) {
        return value.charAt(0);
    }

}
