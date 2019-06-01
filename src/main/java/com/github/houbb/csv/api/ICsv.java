package com.github.houbb.csv.api;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface ICsv<T> {

    /**
     * 写入
     * @param context 上下文
     */
    void write(IWriteContext<T> context);

    /**
     * 读取
     * @param context 上下文
     * @return 列表
     */
    List<T> read(IReadContext<T> context);

}
