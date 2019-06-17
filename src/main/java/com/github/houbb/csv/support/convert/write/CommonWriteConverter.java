package com.github.houbb.csv.support.convert.write;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.ArrayWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.CollectionWriteConverter;
import com.github.houbb.csv.support.convert.write.collection.MapWriteConverter;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;

import java.util.Collection;
import java.util.Map;

/**
 * 通用的写入转换类
 * （1）除了集合类 map/array/collection 单独处理
 * （2）其他类直接使用 {@link com.github.houbb.heaven.util.lang.StringUtil#objectToString(Object, String)} 转为字符串
 * @author binbin.hou
 * @since 0.0.3
 * @see com.github.houbb.csv.support.convert.read.CommonReadConverter 读取转换对应
 */
public class CommonWriteConverter implements IWriteConverter<Object> {

    @Override
    public String convert(Object value) {
        if(ObjectUtil.isNull(value)) {
            return StringUtil.EMPTY;
        }

        final Class type = value.getClass();
        // 特殊的集合类型处理
        if(ClassTypeUtil.isArray(type)) {
            final Object[] arrays = (Object[])value;
            return Instances.singletion(ArrayWriteConverter.class).convert(arrays);
        }
        if(ClassTypeUtil.isMap(type)) {
            final Map maps = (Map)value;
            return Instances.singletion(MapWriteConverter.class).convert(maps);
        }
        if(ClassTypeUtil.isCollection(type)) {
            final Collection collection = (Collection)value;
            return Instances.singletion(CollectionWriteConverter.class).convert(collection);
        }

        return Instances.singletion(StringWriteConverter.class).convert(value);
    }

}