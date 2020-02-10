package com.github.houbb.csv.support.convert.read.collection;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.convert.read.CommonReadConverter;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 集合读取转换类
 * @author binbin.hou
 * @since 0.0.3
 */
public class CollectionReadConverter implements IReadConverter<Collection> {

    @Override
    public Collection convert(SingleReadContext context) {
        final String value = context.value();
        final Field field = context.field();

        final String[] entrys = value.split(CsvConst.SPLIT_OR);

        Collection collection = getCollection(field.getType(), entrys.length);
        final Class componentType = ReflectFieldUtil.getComponentType(field);

        final CommonReadConverter readConverter = Instances.singleton(CommonReadConverter.class);
        for(String string : entrys) {
            final Object object = readConverter.convert(string, componentType, context.escape());
            collection.add(object);
        }
        return collection;
    }

    /**
     * 根据字段类型获取对应的信息
     * （1）list
     * （2）set
     * （3）其他类型
     * @param fieldType 字段类型
     * @param size 大小
     * @return 结果集合
     */
    private Collection getCollection(final Class fieldType, final int size) {
        if(ClassTypeUtil.isList(fieldType)
            && ClassTypeUtil.isAbstractOrInterface(fieldType)) {
            return Guavas.newArrayList(size);
        }

        if(ClassTypeUtil.isSet(fieldType)
            && ClassTypeUtil.isAbstractOrInterface(fieldType)) {
            return Guavas.newHashSet(size);
        }

        if(ClassTypeUtil.isAbstractOrInterface(fieldType)) {
            throw new UnsupportedOperationException("Only support set or list field component type!");
        }
        return (Collection) ClassUtil.newInstance(fieldType);
    }

}
