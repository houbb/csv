package com.github.houbb.csv.convert;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 写入时
 * @author binbin.hou
 * @since 0.0.2
 */
public class WriteDateConvert implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Date value = (Date)context.value();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(value);
    }

}
