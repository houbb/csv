package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * Byte 读取转换器
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ByteReadConverter implements IReadConverter<Byte> {

    @Override
    public Byte convert(String value, Class fieldType) {
        return Byte.valueOf(value);
    }

}
