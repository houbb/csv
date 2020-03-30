package com.github.houbb.csv.api;

import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.heaven.support.sort.ISort;

import java.util.List;

/**
 * 写入上下文
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface IWriteContext<T> {

    /**
     * 写入处理类
     * @return 实现
     * @since 0.0.8
     */
    ICsvWriter writer();

    /**
     * 是否写入标题头
     * @return 是否写入 head 行
     */
    boolean writeHead();

    /**
     * 是否写入 bom 头
     * @return 是否
     * @since 0.0.9
     */
    boolean writeBom();

    /**
     * 排序方式
     * @return 排序方式
     */
    ISort sort();

    /**
     * 待写入的列表
     * @return 待写入的列表
     */
    List<T> list();

    /**
     * 是否进行特殊字符转移
     * @return 是否
     * @since 0.1.16
     */
    boolean escape();

}
