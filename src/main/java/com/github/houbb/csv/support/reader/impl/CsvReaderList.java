package com.github.houbb.csv.support.reader.impl;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.annotation.ThreadSafe;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvReaderList implements ICsvReader {

    /**
     * 字符串列表
     * @since 0.0.8
     */
    private final List<String> strings;

    public CsvReaderList(List<String> strings) {
        this.strings = strings;
    }

    /**
     * 读取内容
     *
     * @return 结果
     * @since 0.0.8
     */
    @Override
    public List<String> read() {
        return strings;
    }

}
