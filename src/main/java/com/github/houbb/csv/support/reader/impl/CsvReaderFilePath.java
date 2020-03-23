package com.github.houbb.csv.support.reader.impl;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.util.io.FileUtil;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@ThreadSafe
public class CsvReaderFilePath implements ICsvReader {

    /**
     * 文件路径信息
     * @since 0.0.8
     */
    private final String filePath;

    /**
     * 文件编码
     * @since 0.0.8
     */
    private final String charset;

    public CsvReaderFilePath(String filePath, String charset) {
        this.filePath = filePath;
        this.charset = charset;
    }

    public CsvReaderFilePath(String filePath) {
        this(filePath, CharsetConst.UTF8);
    }

    /**
     * 读取内容
     *
     * @return 结果
     * @since 0.0.8
     */
    @Override
    public List<String> read() {
        return FileUtil.readAllLines(filePath, charset);
    }

}
