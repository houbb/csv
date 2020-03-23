package com.github.houbb.csv.support.reader.impl;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.util.io.FileUtil;

import java.io.InputStream;
import java.util.List;

/**
 * 输入流
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvReaderInputStream implements ICsvReader {

    /**
     * 输入流
     * @since 0.0.8
     */
    private final InputStream inputStream;

    /**
     * 编码
     * @since 0.0.8
     */
    private final String charset;

    public CsvReaderInputStream(InputStream inputStream, String charset) {
        this.inputStream = inputStream;
        this.charset = charset;
    }

    public CsvReaderInputStream(InputStream inputStream) {
        this(inputStream, CharsetConst.UTF8);
    }

    /**
     * 读取内容
     *
     * @return 结果
     * @since 0.0.8
     */
    @Override
    public List<String> read() {
        return FileUtil.readAllLines(inputStream, charset);
    }

}
