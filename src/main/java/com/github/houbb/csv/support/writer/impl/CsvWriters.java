package com.github.houbb.csv.support.writer.impl;

import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.support.instance.impl.Instances;

/**
 * 控台输出，主要用于调试和自测
 * @author binbin.hou
 * @since 0.0.8
 */
public final class CsvWriters {

    private CsvWriters(){}

    /**
     * 什么都不做
     * @return 实现
     * @since 0.0.8
     */
    public static ICsvWriter none() {
        return Instances.singleton(CsvWriterNone.class);
    }

    /**
     * 命令行输出
     * @return 实现
     * @since 0.0.8
     */
    public static ICsvWriter console() {
        return Instances.singleton(CsvWriterConsole.class);
    }

    /**
     * 文件输出
     * @param filePath 文件路径
     * @param charset 文件编码
     * @return 实现
     * @since 0.0.8
     */
    public static ICsvWriter filePath(final String filePath,
                                      final String charset) {
        return new CsvWriterFilePath(filePath, charset);
    }

    /**
     * 文件输出
     * @param filePath 文件路径
     * @return 实现
     * @since 0.0.8
     */
    public static ICsvWriter filePath(final String filePath) {
        return new CsvWriterFilePath(filePath, CharsetConst.UTF8);
    }

}
