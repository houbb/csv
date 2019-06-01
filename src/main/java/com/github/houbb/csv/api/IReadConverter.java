package com.github.houbb.csv.api;

/**
 * 读取转换器，将 string=>field
 * @author binbin.hou
 * @since 1.0.0
 */
public interface IReadConverter<T> {

    T convert(final String value);

}
