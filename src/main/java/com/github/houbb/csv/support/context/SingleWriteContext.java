package com.github.houbb.csv.support.context;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.heaven.support.sort.ISort;

import java.lang.reflect.Field;

/**
 * 单个元素上下文
 *
 * 后期可能会添加：cache
 * @author binbin.hou
 * @since 0.0.4
 */
public class SingleWriteContext {

    /**
     * 创建一个全新的实例
     * @return 实例
     * @since 0.0.6
     */
    public static SingleWriteContext newInstance() {
        return new SingleWriteContext();
    }

    /**
     * 排序
     */
    private ISort sort;

    /**
     * 单个元素
     */
    private Object element;

    /**
     * 当前字段信息
     * @since 0.0.5
     */
    private Field field;

    /**
     * 当前字段的值
     * @since 0.0.5
     */
    private Object value;

    /**
     * 分隔符号
     * 默认使用：,
     * 一级明细：:
     * 二级明细：::
     * 三级明细:  :::
     * 依次类推。
     *
     * 目的：为了保证 , 号的语意性。
     * @since 0.0.5
     */
    private String split;

    /**
     * 特殊字符转义
     * @since 0.0.6
     */
    private boolean escape;

    /**
     * 注解信息
     * @since 0.1.0
     */
    private Csv csv;

    public ISort sort() {
        return sort;
    }

    public SingleWriteContext sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    public Object element() {
        return element;
    }

    public SingleWriteContext element(Object element) {
        this.element = element;
        return this;
    }

    public Field field() {
        return field;
    }

    public SingleWriteContext field(Field field) {
        this.field = field;
        return this;
    }

    public Object value() {
        return value;
    }

    public SingleWriteContext value(Object value) {
        this.value = value;
        return this;
    }

    public String split() {
        return split;
    }

    public SingleWriteContext split(String split) {
        this.split = split;
        return this;
    }

    public boolean escape() {
        return escape;
    }

    public SingleWriteContext escape(boolean escape) {
        this.escape = escape;
        return this;
    }

    public Csv csv() {
        return csv;
    }

    public SingleWriteContext csv(Csv csv) {
        this.csv = csv;
        return this;
    }
}
