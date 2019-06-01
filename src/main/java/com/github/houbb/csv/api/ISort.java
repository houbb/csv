package com.github.houbb.csv.api;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public interface ISort {

    /**
     * 对字段列表进行排序，返回新的列表
     * @param fields 待排序字段
     * @return 排序后的字段
     */
    List<Field> sort(final List<Field> fields);

}
