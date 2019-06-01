package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Long 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class LongReadConverter implements IReadConverter<Long> {

    @Override
    public Long convert(String value, Class fieldType) {
        return Long.valueOf(value);
    }

}
