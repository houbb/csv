package com.github.houbb.csv.util;

import com.github.houbb.csv.bs.CsvReadBs;
import com.github.houbb.csv.bs.CsvWriteBs;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.csv.support.reader.impl.CsvReaders;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * csv 读写工具类
 * @author binbin.hou
 * @since 0.0.8
 */
public final class CsvHelper {

    private CsvHelper(){}

    /**
     * 读取文件信息
     * @param inputStream 文件输入流
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param inputStream 文件输入流
     * @param charset 文件编码
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param file 文件
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param file 文件
     * @param charset 字符编码
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param filePath 文件路径
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param filePath 文件路径
     * @param charset 字符编码
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param stringList 字符串列表
     * @param tClass 读取类
     * @param <T> 泛型
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
     * @param csvReader csv 读取类
     * @param tClass 读取类
     * @param <T> 泛型
     * @return 结果列表
     * @since 0.0.8
     */
    public static <T> List<T> read(final ICsvReader csvReader,
                                   final Class<T> tClass) {
        return read(csvReader, tClass, CsvConst.DEFAULT_START_INDEX);
    }

    /**
     * 读取文件信息
     * @param csvReader csv 读取类
     * @param tClass 读取类
     * @param startIndex 开始下标
     * @param <T> 泛型
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
     * @param csvReader csv 读取类
     * @param tClass 读取类
     * @param startIndex 开始下标
     * @param endIndex 结束下标
     * @param <T> 泛型
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

}
