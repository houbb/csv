package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Boolean 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class BooleanReadConverter implements IReadConverter<Boolean> {

    @Override
    public Boolean convert(String value, Class fieldType) {
        return Boolean.valueOf(value);
    }

}
