package com.github.houbb.csv.api;

import com.github.houbb.heaven.support.sort.ISort;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface IWriteContext<T> {

    /**
     * 是否写入 bom
     * @return 是否写入 bom
     */
    boolean writeBom();

    /**
     * 是否写入标题头
     * @return 是否写入 head 行
     */
    boolean writeHead();

    /**
     * 文件编码
     * @return 文件编码
     */
    String charset();

    /**
     * 排序方式
     * @return 排序方式
     */
    ISort sort();

    /**
     * 文件路径
     * @return 文件路径
     */
    String path();

    /**
     * 待写入的列表
     * @return 待写入的列表
     */
    List<T> list();

}
