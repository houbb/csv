package com.github.houbb.csv.support.convert.write.collection;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.Collection;

/**
 * 集合写入转换器
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class CollectionWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Collection collection = (Collection)context.value();
        return StringUtil.join(collection, PunctuationConst.OR);
    }

}
