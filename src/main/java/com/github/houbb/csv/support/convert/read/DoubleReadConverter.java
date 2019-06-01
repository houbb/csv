package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Double 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class DoubleReadConverter implements IReadConverter<Double> {

    @Override
    public Double convert(String value, Class fieldType) {
        return Double.valueOf(value);
    }

}
