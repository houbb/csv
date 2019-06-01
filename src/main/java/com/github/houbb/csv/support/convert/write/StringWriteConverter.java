package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class StringWriteConverter implements IWriteConverter<Object> {

    @Override
    public String convert(Object value) {
        if(null == value) {
            return "";
        }
        return value.toString();
    }

}
