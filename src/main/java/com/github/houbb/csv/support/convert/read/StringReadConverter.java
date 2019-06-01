package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class StringReadConverter implements IReadConverter<Object> {

    @Override
    public Object convert(String value) {
        return value;
    }

}
