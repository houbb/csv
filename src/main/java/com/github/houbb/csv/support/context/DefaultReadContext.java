package com.github.houbb.csv.support.context;

import com.github.houbb.csv.api.IReadContext;
import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.support.sort.ISort;

/**
 * 默认读取上下文
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public class DefaultReadContext<T> implements IReadContext<T> {

    /**
     * 读取类
     * @since 0.0.8
     */
    private ICsvReader reader;

    private ISort<T> sort;

    private Class<T> readClass;

    private int startIndex;

    private int endIndex;

    private boolean escape;

    public DefaultReadContext<T> newInstance() {
        return new DefaultReadContext<>();
    }

    @Override
    public ICsvReader reader() {
        return reader;
    }

    public DefaultReadContext<T> reader(ICsvReader reader) {
        this.reader = reader;
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

    @Override
    public boolean escape() {
        return escape;
    }

    public DefaultReadContext<T> escape(boolean escape) {
        this.escape = escape;
        return this;
    }
}
