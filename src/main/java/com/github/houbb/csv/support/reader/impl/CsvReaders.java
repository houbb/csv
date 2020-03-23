package com.github.houbb.csv.support.reader.impl;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.annotation.CommonEager;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@CommonEager
public final class CsvReaders {

    private CsvReaders(){}

    /**
     * 基于字符串列表的处理
     * @param lines 字符串列表
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader list(final List<String> lines) {
        return new CsvReaderList(lines);
    }

    /**
     * 基于字符串列表的处理
     * @param file 文件信息
     * @param charset 文件编码
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader file(final File file,
                                  final String charset) {
        return new CsvReaderFile(file, charset);
    }

    /**
     * 基于字符串列表的处理
     * @param file 文件信息
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader file(final File file) {
        return new CsvReaderFile(file);
    }

    /**
     * 基于文件路径的处理
     * @param file 文件信息
     * @param charset 文件编码
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader filePath(final String file,
                                  final String charset) {
        return new CsvReaderFilePath(file, charset);
    }

    /**
     * 基于文件路径的处理
     * @param file 文件信息
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader filePath(final String file) {
        return new CsvReaderFilePath(file);
    }

    /**
     * 基于文件输入流的处理
     * @param inputStream 文件输入流
     * @param charset 文件编码
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader inputStream(final InputStream inputStream,
                                      final String charset) {
        return new CsvReaderInputStream(inputStream, charset);
    }

    /**
     * 基于文件输入流的处理
     * @param inputStream 文件输入流
     * @return 结果信息
     * @since 0.0.8
     */
    public static ICsvReader inputStream(final InputStream inputStream) {
        return new CsvReaderInputStream(inputStream);
    }

}
