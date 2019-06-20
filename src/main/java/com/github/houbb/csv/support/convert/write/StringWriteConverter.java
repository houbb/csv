package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.util.CsvInnerUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * 字符串写入转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class StringWriteConverter implements IWriteConverter, IHandler<Pair<Object, Boolean>, String> {

    @Override
    public String convert(SingleWriteContext context) {
        Pair<Object, Boolean> pair = Pair.of(context.value(), context.escape());
        return this.handle(pair);
    }

    @Override
    public String handle(Pair<Object, Boolean> objectBooleanPair) {
        final Object value = objectBooleanPair.getValueOne();
        final Boolean escape = objectBooleanPair.getValueTwo();

        String result = StringUtil.objectToString(value, StringUtil.EMPTY);
        // 特殊字符转换 v0.0.6
        if(escape) {
            result = CsvInnerUtil.replaceAllSpecial(result);
        }
        return result;
    }
}
