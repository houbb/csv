package com.github.houbb.csv.support.convert.write.collection;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.Map;
import java.util.Set;

/**
 * MAP 写入转换器
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class MapWriteConverter implements IWriteConverter<Map> {

    @Override
    public String convert(Map value) {
        Set<Map.Entry> entrySet = value.entrySet();
        return StringUtil.join(entrySet, PunctuationConst.OR);
    }

}
