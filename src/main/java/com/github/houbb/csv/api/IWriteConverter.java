package com.github.houbb.csv.api;

import com.github.houbb.csv.support.context.SingleWriteContext;

/**
 * 读取转换器，将 field 转换为 string
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWriteConverter {

    /**
     * 执行转换
     * @param context 上下文
     * @return 字符串
     */
    String convert(final SingleWriteContext context);

}
