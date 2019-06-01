package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Short 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ShortReadConverter implements IReadConverter<Short> {

    @Override
    public Short convert(String value, Class fieldType) {
        return Short.valueOf(value);
    }

}
