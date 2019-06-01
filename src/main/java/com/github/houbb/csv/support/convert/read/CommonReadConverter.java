package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.PrimitiveUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class CommonReadConverter implements IReadConverter<Object> {

    /**
     * 转换器映射关系
     */
    private static final Map<Class, IReadConverter> CONVERTER_MAP = new HashMap<>();

    static {
        CONVERTER_MAP.put(String.class, InstanceFactory.getInstance().singleton(StringReadConverter.class));
        CONVERTER_MAP.put(Byte.class, InstanceFactory.getInstance().singleton(ByteReadConverter.class));
        CONVERTER_MAP.put(Boolean.class, InstanceFactory.getInstance().singleton(BooleanReadConverter.class));
        CONVERTER_MAP.put(Short.class, InstanceFactory.getInstance().singleton(ShortReadConverter.class));
        CONVERTER_MAP.put(Integer.class, InstanceFactory.getInstance().singleton(IntegerReadConverter.class));
        CONVERTER_MAP.put(Long.class, InstanceFactory.getInstance().singleton(LongReadConverter.class));
        CONVERTER_MAP.put(Float.class, InstanceFactory.getInstance().singleton(FloatReadConverter.class));
        CONVERTER_MAP.put(Double.class, InstanceFactory.getInstance().singleton(DoubleReadConverter.class));
        CONVERTER_MAP.put(Character.class, InstanceFactory.getInstance().singleton(CharacterReadConverter.class));
    }

    @Override
    public Object convert(String value, Class fieldType) {
        //1. 为空判断
        if(StringUtil.isEmpty(value)) {
            return null;
        }

        //2. 维护一个映射关系
        Class refType = fieldType;
        if(fieldType.isPrimitive()) {
            refType = PrimitiveUtil.getReferenceType(fieldType);
        }
        IReadConverter readConverter = CONVERTER_MAP.get(refType);
        if(ObjectUtil.isNotNull(readConverter)) {
            return readConverter.convert(value, fieldType);
        }

        //3. 不属于基本类型，则直接返回 null
        return null;
    }


}
