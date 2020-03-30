package com.github.houbb.csv.support.writer;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
public interface ICsvWriter {

    /**
     * 针对字符串列表的写入
     * @param context 写入上下文
     * @since 0.0.8
     */
    void write(final ICsvWriterContext context);

}
