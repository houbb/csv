package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Float 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class FloatReadConverter implements IReadConverter<Float> {

    @Override
    public Float convert(String value, Class fieldType) {
        return Float.valueOf(value);
    }

}
