package com.github.houbb.csv.support.writer.impl;

import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.support.writer.ICsvWriterContext;
import com.github.houbb.heaven.annotation.ThreadSafe;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvWriterNone implements ICsvWriter {

    /**
     * 针对字符串列表的写入
     *
     * @param context 上下文
     * @since 0.0.8
     */
    @Override
    public void write(final ICsvWriterContext context) {
        //do nothing
    }

}
