package com.github.houbb.csv.support.context;

import com.github.houbb.csv.api.IWriteContext;
import com.github.houbb.heaven.support.sort.ISort;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class DefaultWriteContext<T> implements IWriteContext<T> {

    private boolean writeBom;

    private boolean writeHead;

    private String charset;

    private ISort sort;

    private String path;

    private List<T> list;

    @Override
    public boolean writeBom() {
        return writeBom;
    }

    public DefaultWriteContext<T> writeBom(boolean writeBom) {
        this.writeBom = writeBom;
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
    public String charset() {
        return charset;
    }

    public DefaultWriteContext<T> charset(String charset) {
        this.charset = charset;
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
    public String path() {
        return path;
    }

    public DefaultWriteContext<T> path(String path) {
        this.path = path;
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
}
