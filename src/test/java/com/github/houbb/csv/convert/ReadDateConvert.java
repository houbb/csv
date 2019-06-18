package com.github.houbb.csv.convert;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.context.SingleReadContext;

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
    public Date convert(SingleReadContext context) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.parse(context.value());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
