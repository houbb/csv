package com.github.houbb.csv.support.writer;

import java.util.List;

/**
 * 写入上下文
 * @author binbin.hou
 * @since 0.0.9
 */
public interface ICsvWriterContext {

    /**
     * 是否写入标题头
     * @return 是否写入 head 行
     * @since 0.0.9
     */
    boolean writeHead();

    /**
     * 是否写入 bom 头
     * @return 是否
     * @since 0.0.9
     */
    boolean writeBom();

    /**
     * 头信息
     * @return 头信息
     * @since 0.0.9
     */
    String head();

    /**
     * 待写入的字符串列表
     * @return 列表
     * @since 0.0.9
     */
    List<String> list();

}
