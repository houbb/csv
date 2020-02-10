package com.github.houbb.csv.support.convert.write.collection;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.write.StringWriteConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.*;

/**
 * MAP 写入转换器
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class MapWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final List<String> stringList = buildStringList(context);
        return StringUtil.join(stringList, PunctuationConst.OR);
    }

    /**
     * 构建字符串列表
     *
     * @param context 上下文
     * @return 结果列表
     * @since 0.0.6
     */
    private List<String> buildStringList(SingleWriteContext context) {
        final Object value = context.value();
        if (ObjectUtil.isNull(value)) {
            return Collections.emptyList();
        }

        final Map map = (Map) context.value();
        List<String> stringList = Guavas.newArrayList(map.size());
        final boolean escape = context.escape();
        // 这里直接使用 entry 会提示错误，暂时使用 key 代替。
        for (Object key : map.keySet()) {
            final Pair<Object, Boolean> keyPair = Pair.of(key, escape);
            final Pair<Object, Boolean> valuePair = Pair.of(map.get(key), escape);
            final String keyString = Instances.singleton(StringWriteConverter.class)
                    .handle(keyPair);
            final String valueString = Instances.singleton(StringWriteConverter.class)
                    .handle(valuePair);
            final String string = keyString + CsvConst.EQUALS + valueString;
            stringList.add(string);
        }
        return stringList;
    }

}
