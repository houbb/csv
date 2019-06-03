package com.github.houbb.csv.convert;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.util.util.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 读取时
 * @author binbin.hou
 * @since 0.0.2
 */
public class ReadDateConvert implements IReadConverter<Date> {

    @Override
    public Date convert(String value, Class fieldType) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
