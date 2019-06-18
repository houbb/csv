package com.github.houbb.csv.support.context;

import com.github.houbb.heaven.support.sort.ISort;

import java.lang.reflect.Field;

/**
 * 单个元素读取上下文
 * @author binbin.hou
 * @since 0.0.4
 */
public class SingleReadContext<T> {

    /**
     * 字符串的值
     */
    private String value;

    /**
     * 当前字段信息
     */
    private Field field;

    /**
     * 字段类型
     */
    private Class fieldType;

    /**
     * 类信息
     */
    private Class<T> classType;

    /**
     * 排序信息
     */
    private ISort<T> sort;

    public String value() {
        return value;
    }

    public SingleReadContext<T> value(String value) {
        this.value = value;
        return this;
    }

    public Field field() {
        return field;
    }

    public SingleReadContext<T> field(Field field) {
        this.field = field;
        return this;
    }

    public Class fieldType() {
        return fieldType;
    }

    public SingleReadContext<T> fieldType(Class fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public Class<T> classType() {
        return classType;
    }

    public SingleReadContext<T> classType(Class<T> classType) {
        this.classType = classType;
        return this;
    }

    public ISort<T> sort() {
        return sort;
    }

    public SingleReadContext<T> sort(ISort<T> sort) {
        this.sort = sort;
        return this;
    }
}
