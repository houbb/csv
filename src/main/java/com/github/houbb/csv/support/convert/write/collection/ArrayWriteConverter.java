package com.github.houbb.csv.support.convert.write.collection;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * 数组写入转换器
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class ArrayWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Object[] arrays = (Object[])context.value();
        return StringUtil.join(arrays, PunctuationConst.OR);
    }

}
