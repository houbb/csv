package com.github.houbb.csv.api;

import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.support.sort.ISort;

/**
 * 读取上下文
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IReadContext<T> {

    /**
     * 读取类
     * @return 实现
     * @since 0.0.8
     */
    ICsvReader reader();

    /**
     * 排序方式
     * @return 排序方式
     */
    ISort<T> sort();

    /**
     * 读取的类型
     * @return 类型
     */
    Class<T> readClass();

    /**
     * 开始的下标
     * 备注：参见 {@link java.io.RandomAccessFile} 随机读写文件
     * @return 开始的下标
     */
    int startIndex();

    /**
     * 结束的下标
     * @return 结束的下标
     */
    int endIndex();

    /**
     * 是否进行特殊字符转义
     * @since 0.0.6
     * @return 是否
     */
    boolean escape();

}
