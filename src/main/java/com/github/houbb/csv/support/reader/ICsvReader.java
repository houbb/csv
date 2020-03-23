package com.github.houbb.csv.support.reader;

import com.github.houbb.heaven.annotation.CommonEager;

import java.util.List;

/**
 * 可以抽象为 readers
 *
 * 1. readString
 * 2. readLines
 * @author binbin.hou
 * @since 0.0.8
 */
@CommonEager
public interface ICsvReader {

    /**
     * 读取内容
     * @return 结果
     * @since 0.0.8
     */
    List<String> read();

}
