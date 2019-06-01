package com.github.houbb.csv.api;

/**
 * 读取转换器，将 field=>string
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IWriteConverter<T> {

    /**
     * 执行转换
     * @param value 对象值
     * @return 字符串
     */
    String convert(final T value);

}
