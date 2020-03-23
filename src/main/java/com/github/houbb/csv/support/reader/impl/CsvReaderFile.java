package com.github.houbb.csv.support.reader.impl;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.util.io.FileUtil;

import java.io.File;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvReaderFile implements ICsvReader {

    /**
     * 文件信息
     * @since 0.0.8
     */
    private final File file;

    /**
     * 文件编码
     * @since 0.0.8
     */
    private final String charset;

    public CsvReaderFile(File file, String charset) {
        this.file = file;
        this.charset = charset;
    }

    public CsvReaderFile(File file) {
        this(file, CharsetConst.UTF8);
    }

    /**
     * 读取内容
     *
     * @return 结果
     * @since 0.0.8
     */
    @Override
    public List<String> read() {
        return FileUtil.readAllLines(file, charset);
    }

}
