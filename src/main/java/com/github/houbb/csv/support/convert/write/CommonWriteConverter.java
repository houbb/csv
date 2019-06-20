package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.write.collection.ArrayWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.CollectionWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.MapWriteConverter;
import com.github.houbb.csv.support.convert.write.entry.EntryWriteConverter;
import com.github.houbb.csv.util.CsvFieldUtil;
import com.github.houbb.csv.util.CsvInnerUtil;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;

/**
 * 通用的写入转换类
 * （1）除了集合类 map/array/collection 单独处理
 * （2）其他类直接使用 {@link com.github.houbb.heaven.util.lang.StringUtil#objectToString(Object, String)} 转为字符串
 * @author binbin.hou
 * @since 0.0.3
 * @see com.github.houbb.csv.support.convert.read.CommonReadConverter 读取转换对应
 */
public class CommonWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Object value = context.value();
        if(ObjectUtil.isNull(value)) {
            return StringUtil.EMPTY;
        }

        final Class type = value.getClass();
        // 特殊的集合类型处理
        if(ClassTypeUtil.isArray(type)) {
            return Instances.singletion(ArrayWriteConverter.class).convert(context);
        }
        if(ClassTypeUtil.isMap(type)) {
            return Instances.singletion(MapWriteConverter.class).convert(context);
        }
        if(ClassTypeUtil.isCollection(type)) {
            return Instances.singletion(CollectionWriteConverter.class).convert(context);
        }

        // 当前字段指定为 @CsvEntry 且为对象
        if(CsvFieldUtil.isEntryAble(context.field())) {
            final String split = CsvInnerUtil.getNextSplit(context.split());
            SingleWriteContext singleWriteContext = SingleWriteContext.newInstance()
                    .sort(context.sort())
                    .element(context.value())
                    .split(split)
                    .escape(context.escape());
            return Instances.singletion(EntryWriteConverter.class).convert(singleWriteContext);
        }

        return Instances.singletion(StringWriteConverter.class).convert(context);
    }

}
