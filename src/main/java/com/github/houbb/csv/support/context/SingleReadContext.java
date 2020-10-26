package com.github.houbb.csv.support.context;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.heaven.support.sort.ISort;

import java.lang.reflect.Field;

/**
 * 单个元素读取上下文
 * @author binbin.hou
 * @since 0.0.4
 */
public class SingleReadContext {

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
    private Class classType;

    /**
     * 排序信息
     */
    private ISort sort;

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
     * 特殊字符的转义
     * @since 0.0.6
     */
    private boolean escape;

    /**
     * csv 注解信息
     * @since 0.1.0
     */
    private Csv csv;

    /**
     * 创建新的实例
     * @return 新的实例
     * @since 0.0.6
     */
    public static SingleReadContext newInstance() {
        return new SingleReadContext();
    }

    public String value() {
        return value;
    }

    public SingleReadContext value(String value) {
        this.value = value;
        return this;
    }

    public Field field() {
        return field;
    }

    public SingleReadContext field(Field field) {
        this.field = field;
        return this;
    }

    public Class fieldType() {
        return fieldType;
    }

    public SingleReadContext fieldType(Class fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public Class classType() {
        return classType;
    }

    public SingleReadContext classType(Class classType) {
        this.classType = classType;
        return this;
    }

    public ISort sort() {
        return sort;
    }

    public SingleReadContext sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    public String split() {
        return split;
    }

    public SingleReadContext split(String split) {
        this.split = split;
        return this;
    }

    public boolean escape() {
        return escape;
    }

    public SingleReadContext escape(boolean escape) {
        this.escape = escape;
        return this;
    }

    public Csv csv() {
        return csv;
    }

    public SingleReadContext csv(Csv csv) {
        this.csv = csv;
        return this;
    }
}
