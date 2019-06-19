package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * 字符串写入转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class StringWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Object value = context.value();
        return StringUtil.objectToString(value, StringUtil.EMPTY);
    }

}
