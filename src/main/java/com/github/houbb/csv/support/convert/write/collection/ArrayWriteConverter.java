package com.github.houbb.csv.support.convert.write.collection;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.write.StringWriteConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.Collections;
import java.util.List;

/**
 * 数组写入转换器
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class ArrayWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final List<String> stringList = buildStringList(context);
        return StringUtil.join(stringList, PunctuationConst.OR);
    }

    /**
     * 构建字符串列表
     * @param context 上下文
     * @return 结果列表
     * @since 0.0.6
     */
    private List<String> buildStringList(SingleWriteContext context) {
        final Object value = context.value();
        if(ObjectUtil.isNull(value)) {
            return Collections.emptyList();
        }

        final Object[] arrays = (Object[])value;
        List<String> stringList = Guavas.newArrayList(arrays.length);
        final boolean escape = context.escape();
        for(Object object : arrays) {
            final Pair<Object, Boolean> pair = Pair.of(object, escape);
            final String string = Instances.singleton(StringWriteConverter.class)
                    .handle(pair);
            stringList.add(string);
        }
        return stringList;
    }

}
