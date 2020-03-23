package com.github.houbb.csv.support.writer;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
public interface ICsvWriter {

    /**
     * 针对字符串列表的写入
     * @param list 列表
     * @since 0.0.8
     */
    void write(final List<String> list);

}
