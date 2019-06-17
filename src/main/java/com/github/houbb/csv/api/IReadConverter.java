package com.github.houbb.csv.api;

import java.lang.reflect.Field;

/**
 * 读取转换器，将 string 转换为 field
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IReadConverter<T> {

    /**
     * 执行转换
     * @param value 字符串值
     * @param field 字段类型
     * @return 结果
     */
    T convert(final String value, final Field field);

}
