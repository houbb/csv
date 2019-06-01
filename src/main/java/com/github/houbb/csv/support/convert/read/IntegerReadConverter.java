package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Integer 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class IntegerReadConverter implements IReadConverter<Integer> {

    @Override
    public Integer convert(String value, Class fieldType) {
        return Integer.valueOf(value);
    }

}
