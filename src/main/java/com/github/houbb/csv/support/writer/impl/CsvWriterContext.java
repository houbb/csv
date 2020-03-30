package com.github.houbb.csv.support.writer.impl;

import com.github.houbb.csv.support.writer.ICsvWriterContext;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.9
 */
public class CsvWriterContext implements ICsvWriterContext {

    /**
     * 是否写入标题头
     * @since 0.0.9
     */
    private boolean writeHead;

    /**
     * 是否写入 bom 头
     * @since 0.0.9
     */
    private boolean writeBom;

    /**
     * 头信息
     * @since 0.0.9
     */
    private String head;

    /**
     * 待写入的字符串列表
     * @since 0.0.9
     */
    private List<String> list;

    /**
     * 新建实例
     * @return this
     * @since 0.0.9
     */
    public static CsvWriterContext newInstance() {
        return new CsvWriterContext();
    }

    @Override
    public boolean writeHead() {
        return writeHead;
    }

    public CsvWriterContext writeHead(boolean writeHead) {
        this.writeHead = writeHead;
        return this;
    }

    @Override
    public boolean writeBom() {
        return writeBom;
    }

    public CsvWriterContext writeBom(boolean writeBom) {
        this.writeBom = writeBom;
        return this;
    }

    @Override
    public String head() {
        return head;
    }

    public CsvWriterContext head(String head) {
        this.head = head;
        return this;
    }

    @Override
    public List<String> list() {
        return list;
    }

    public CsvWriterContext list(List<String> list) {
        this.list = list;
        return this;
    }
}
