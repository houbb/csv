package com.github.houbb.csv.util;

import com.github.houbb.csv.bs.CsvReadBs;
import com.github.houbb.csv.bs.CsvWriteBs;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.csv.support.reader.impl.CsvReaders;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.support.writer.impl.CsvWriters;
import com.github.houbb.heaven.constant.CharsetConst;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * csv 读写工具类
 *
 * @author binbin.hou
 * @since 0.0.8
 */
public final class CsvHelper {

    private CsvHelper() {
    }

    /**
     * 读取文件信息
     *
     * @param inputStream 文件输入流
     * @param tClass      读取类
     * @param <T>         泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final InputStream inputStream,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.inputStream(inputStream);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param inputStream 文件输入流
     * @param charset     文件编码
     * @param tClass      读取类
     * @param <T>         泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final InputStream inputStream,
                                   final String charset,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.inputStream(inputStream, charset);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param file   文件
     * @param tClass 读取类
     * @param <T>    泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final File file,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.file(file);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param file    文件
     * @param charset 字符编码
     * @param tClass  读取类
     * @param <T>     泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final File file,
                                   final String charset,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.file(file, charset);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param filePath 文件路径
     * @param tClass   读取类
     * @param <T>      泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final String filePath,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.filePath(filePath);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param filePath 文件路径
     * @param charset  字符编码
     * @param tClass   读取类
     * @param <T>      泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final String filePath,
                                   final String charset,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.filePath(filePath, charset);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param stringList 字符串列表
     * @param tClass     读取类
     * @param <T>        泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final List<String> stringList,
                                   final Class<T> tClass) {
        final ICsvReader reader = CsvReaders.list(stringList);
        return read(reader, tClass);
    }

    /**
     * 读取文件信息
     *
     * @param csvReader csv 读取类
     * @param tClass    读取类
     * @param <T>       泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final ICsvReader csvReader,
                                   final Class<T> tClass) {
        return read(csvReader, tClass, CsvConst.DEFAULT_START_INDEX);
    }

    /**
     * 读取文件信息
     *
     * @param csvReader  csv 读取类
     * @param tClass     读取类
     * @param startIndex 开始下标
     * @param <T>        泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final ICsvReader csvReader,
                                   final Class<T> tClass,
                                   final int startIndex) {
        return read(csvReader, tClass, startIndex, CsvConst.DEFAULT_END_INDEX);
    }

    /**
     * 读取文件信息
     *
     * @param csvReader  csv 读取类
     * @param tClass     读取类
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     * @param <T>        泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final ICsvReader csvReader,
                                   final Class<T> tClass,
                                   final int startIndex,
                                   final int endIndex) {
        return CsvReadBs.newInstance()
                .reader(csvReader)
                .startIndex(startIndex)
                .endIndex(endIndex)
                .read(tClass);
    }


    /**
     * 文件写入
     * 1. 默认写入表头
     * 2. 什么都不做
     *
     * @param list 文件列表
     * @param <T>  泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<String> write(final List<T> list) {
        return write(list, CsvWriters.none());
    }

    /**
     * 文件写入
     * 1. 默认写入表头
     *
     * @param list     文件列表
     * @param filePath 文件路径
     * @param <T>      泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<String> write(final List<T> list,
                                         final String filePath) {
        return write(list, filePath, CharsetConst.UTF8);
    }

    /**
     * 文件写入
     * 1. 默认写入表头
     *
     * @param list     文件列表
     * @param filePath 文件路径
     * @param charset  文件编码
     * @param <T>      泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<String> write(final List<T> list,
                                         final String filePath,
                                         final String charset) {
        final ICsvWriter csvWriter = CsvWriters.filePath(filePath, charset);
        return write(list, csvWriter);
    }

    /**
     * 文件写入
     * 1. 默认写入表头
     *
     * @param list      文件列表
     * @param csvWriter 写入类实现
     * @param <T>       泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<String> write(final List<T> list,
                                         final ICsvWriter csvWriter) {
        return write(list, csvWriter, true);
    }

    /**
     * 文件写入
     *
     * @param list      文件列表
     * @param csvWriter 写入类实现
     * @param writeHead 是否写入头信息
     * @param <T>       泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<String> write(final List<T> list,
                                         final ICsvWriter csvWriter,
                                         final boolean writeHead) {
        return CsvWriteBs.newInstance()
                .writer(csvWriter)
                .writeHead(writeHead)
                .write(list);
    }

}
