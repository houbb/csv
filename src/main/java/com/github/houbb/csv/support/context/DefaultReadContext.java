package com.github.houbb.csv.support.context;

import com.github.houbb.csv.api.IReadContext;
import com.github.houbb.heaven.support.sort.ISort;

/**
 * 默认读取上下文
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public class DefaultReadContext<T> implements IReadContext<T> {

    private String charset;

    private ISort<T> sort;

    private String path;

    private Class<T> readClass;

    private int startIndex;

    private int endIndex;

    public DefaultReadContext<T> newInstance() {
        return new DefaultReadContext<>();
    }

    @Override
    public String charset() {
        return charset;
    }

    public DefaultReadContext<T> charset(String charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public ISort<T> sort() {
        return sort;
    }

    public DefaultReadContext<T> sort(ISort<T> sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public String path() {
        return path;
    }

    public DefaultReadContext<T> path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public Class<T> readClass() {
        return readClass;
    }

    public DefaultReadContext<T> readClass(Class<T> readClass) {
        this.readClass = readClass;
        return this;
    }

    @Override
    public int startIndex() {
        return startIndex;
    }

    public DefaultReadContext<T> startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    @Override
    public int endIndex() {
        return endIndex;
    }

    public DefaultReadContext<T> endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }
}
