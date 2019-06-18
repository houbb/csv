package com.github.houbb.csv.support.convert.read.collection;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.convert.read.CommonReadConverter;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * Map 读取转换类
 * @author binbin.hou
 * @since 0.0.3
 */
public class MapReadConverter implements IReadConverter<Map> {

    @Override
    public Map convert(SingleReadContext context) {
        final String value = context.value();
        final Field field = context.field();

        //entry
        String[] entryStrings = value.split(CsvConst.SPLIT_OR);
        if(ArrayUtil.isEmpty(entryStrings)) {
            return Collections.emptyMap();
        }

        // 获取对应的 map 实例
        final Map map = getMap(field, entryStrings.length);

        final Class keyType = ReflectFieldUtil.getComponentType(field, 0);
        final Class valueType = ReflectFieldUtil.getComponentType(field, 1);

        final CommonReadConverter readConverter = Instances.singletion(CommonReadConverter.class);

        for(String entryStr : entryStrings) {
            if(StringUtil.isEmpty(entryStr)) {
                continue;
            }

            String[] kvs = entryStr.split("=");
            if(ArrayUtil.isEmpty(kvs)) {
                continue;
            }

            Object keyObj = readConverter.convert(kvs[0], keyType);
            Object valueObj = readConverter.convert(kvs[1], valueType);
            map.put(keyObj, valueObj);
        }

        return map;
    }

    /**
     * 获取对应的 map
     * @param field 字段信息
     * @param size 大小
     * @return map
     */
    private Map getMap(final Field field,
                       final int size) {
        final Class fieldType = field.getType();

        // 抽象类: 暂时只支持 hashMap
        if(ClassTypeUtil.isAbstractOrInterface(fieldType)) {
            return Guavas.newHashMap(size);
        }

        try {
            return (Map) fieldType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommonRuntimeException(e);
        }
    }

}
