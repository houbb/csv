package com.github.houbb.csv.support.context;

import com.github.houbb.heaven.support.sort.ISort;

/**
 * 单个元素上下文
 *
 * 后期可能会添加：cache
 * @author binbin.hou
 * @since 0.0.4
 * @param <T> 泛型
 */
public class SingleWriteContext<T> {

    /**
     * 排序
     */
    private ISort sort;

    /**
     * 单个元素
     */
    private T element;

    public ISort sort() {
        return sort;
    }

    public SingleWriteContext<T> sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    public T element() {
        return element;
    }

    public SingleWriteContext<T> element(T element) {
        this.element = element;
        return this;
    }
}
