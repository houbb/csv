package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.CSV;
import com.github.houbb.heaven.annotation.CommonEager;

import java.lang.reflect.Field;

/**
 * @author binbin.hou
 * @since 1.0.0
 *
 * 使用泛型，添加公用注解信息。
 */
@CommonEager
public class FieldBean {

    /**
     * 字段信息
     */
    private Field field;

    /**
     * 注解信息
     */
    private CSV csv;

    public Field field() {
        return field;
    }

    public FieldBean field(Field field) {
        this.field = field;
        return this;
    }

    public CSV csv() {
        return csv;
    }

    public FieldBean csv(CSV csv) {
        this.csv = csv;
        return this;
    }
}
