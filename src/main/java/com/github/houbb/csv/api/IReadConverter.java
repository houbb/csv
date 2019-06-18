package com.github.houbb.csv.api;

import com.github.houbb.csv.support.context.SingleReadContext;

/**
 * 读取转换器，将 string 转换为 field
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IReadConverter<T> {

    /**
     * 执行转换
     * @param context 上下文
     * @return 结果
     */
    T convert(final SingleReadContext<T> context);

}
