package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.annotation.CsvEntry;
import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.write.collection.ArrayWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.CollectionWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.MapWriteConverter;
import com.github.houbb.csv.support.convert.write.entry.EntryWriteConverter;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Field;

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
        final Object value = context.element();
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
        if(isEntryAble(context)) {
            final String split = getNextSplit(context.split());
            SingleWriteContext singleWriteContext = new SingleWriteContext();
            singleWriteContext.sort(context.sort());
            singleWriteContext.element(context.value());
            singleWriteContext.split(split);
            return Instances.singletion(EntryWriteConverter.class).convert(singleWriteContext);
        }

        return Instances.singletion(StringWriteConverter.class).convert(context);
    }

    /**
     * 是否进行明细
     * @param context 上下文
     * @return 是否
     */
    private boolean isEntryAble(final SingleWriteContext context) {
        final Field field = context.field();
        return ReflectFieldUtil.isAnnotationPresent(field, CsvEntry.class)
                && ClassTypeUtil.isJavaBean(field.getType());
    }

    /**
     * 获取下一个分隔符号
     * @param preSplit 原来的分隔符号
     * @return 下一个分隔符
     */
    private String getNextSplit(final String preSplit) {
        if(CsvConst.COMMA.equals(preSplit)) {
            return CsvConst.ENTRY_SPLIT_UNIT;
        }
        if(preSplit.startsWith(CsvConst.ENTRY_SPLIT_UNIT)) {
            final int times = preSplit.length()+1;
            return CharUtil.repeat(CsvConst.ENTRY_SPLIT_UNIT_CHAR, times);
        }
        throw new UnsupportedOperationException("暂时不支持的分隔符!");
    }

}
