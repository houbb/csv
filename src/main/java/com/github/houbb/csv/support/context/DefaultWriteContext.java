package com.github.houbb.csv.support.context;

import com.github.houbb.csv.api.IWriteContext;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.heaven.support.sort.ISort;

import java.util.List;

/**
 * 默认写入上下文
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public class DefaultWriteContext<T> implements IWriteContext<T> {

    /**
     * 写入处理类
     * @since 0.0.8
     */
    private ICsvWriter writer;

    /**
     * 是否写入表头
     * @since 0.0.8
     */
    private boolean writeHead;

    /**
     * 是否写入 bom 头
     * @since 0.0.9
     */
    private boolean writeBom;

    /**
     * 排序算法
     * @since 0.0.8
     */
    private ISort sort;

    /**
     * 待写入列表
     * @since 0.0.8
     */
    private List<T> list;

    /**
     * 是否启动转换
     * @since 0.0.6
     */
    private boolean escape;

    /**
     * @since 0.2.0
     */
    private char quoteChar;

    @Override
    public ICsvWriter writer() {
        return writer;
    }

    public DefaultWriteContext<T> writer(ICsvWriter writer) {
        this.writer = writer;
        return this;
    }

    @Override
    public boolean writeHead() {
        return writeHead;
    }

    public DefaultWriteContext<T> writeHead(boolean writeHead) {
        this.writeHead = writeHead;
        return this;
    }

    @Override
    public boolean writeBom() {
        return writeBom;
    }

    public DefaultWriteContext<T> writeBom(boolean writeBom) {
        this.writeBom = writeBom;
        return this;
    }

    @Override
    public ISort sort() {
        return sort;
    }

    public DefaultWriteContext<T> sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public List<T> list() {
        return list;
    }

    public DefaultWriteContext<T> list(List<T> list) {
        this.list = list;
        return this;
    }

    @Override
    public boolean escape() {
        return escape;
    }

    public DefaultWriteContext<T> escape(boolean escape) {
        this.escape = escape;
        return this;
    }

    @Override
    public char quoteChar() {
        return quoteChar;
    }

    public DefaultWriteContext<T> quoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
        return this;
    }
}
