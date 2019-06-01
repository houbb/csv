package com.github.houbb.csv.api;

/**
 * 读取转换器，将 field=>string
 * @author binbin.hou
 * @since 1.0.0
 */
public interface IWriteConverter<T> {

    String convert(final T value);

}
