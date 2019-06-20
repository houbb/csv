package com.github.houbb.csv.support.convert.read.collection;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.convert.read.CommonReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * 数组读取
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class ArrayReadConverter implements IReadConverter<Object> {

    @Override
    public Object convert(SingleReadContext context) {
        final String value = context.value();
        final Field field = context.field();

        String[] strings = value.split(CsvConst.SPLIT_OR);
        Class componentType = ReflectFieldUtil.getComponentType(field);
        Object[] arrays = (Object[]) Array.newInstance(componentType, strings.length);

        final CommonReadConverter readConverter = Instances.singletion(CommonReadConverter.class);
        for (int i = 0; i < strings.length; i++) {
            final Object entryValue = readConverter.convert(strings[i], componentType, context.escape());
            Array.set(arrays, i, entryValue);
        }
        return arrays;
    }

}
