package com.github.houbb.csv.api;

import java.io.File;

/**
 * 读取转换器，将 string=>field
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IReadConverter<T> {

    /**
     * 执行转换
     * @param value 字符串值
     * @param fieldType 字段类型
     * @return 结果
     */
    T convert(final String value,
              final Class fieldType);

}
