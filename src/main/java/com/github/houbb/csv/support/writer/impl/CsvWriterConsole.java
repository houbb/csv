package com.github.houbb.csv.support.writer.impl;

import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.heaven.annotation.ThreadSafe;

import java.util.List;

/**
 * 控台输出，主要用于调试和自测
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvWriterConsole implements ICsvWriter {

    /**
     * 针对字符串列表的写入
     *
     * @param list 列表
     * @since 0.0.8
     */
    @Override
    public void write(final List<String> list) {
        //do nothing
        for(String line : list) {
            System.out.println(line);
        }
    }

}
