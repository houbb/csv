package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * 字符串写入转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class StringWriteConverter implements IWriteConverter<Object> {

    @Override
    public String convert(Object value) {
        return StringUtil.objectToString(value, StringUtil.EMPTY);
    }

}
