package com.github.houbb.csv.annotation;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.convert.read.StringReadConverter;
import com.github.houbb.csv.support.convert.write.StringWriteConverter;

import java.lang.annotation.*;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.FIELD)
public @interface CSV {

    /**
     * 读取是否需要
     * @return 是
     */
    boolean readRequire() default true;

    /**
     * 写入是否需要
     * @return 是
     */
    boolean writeRequire() default true;

    /**
     * 字段显示名称
     * 1. 默认使用 field.name
     * @return 显示名称
     */
    String label() default "";

    /**
     * 读取转换
     * @return 处理实现类
     */
    Class<? extends IReadConverter> readConverter() default StringReadConverter.class;

    /**
     * 写入转换
     * @return 处理实现类
     */
    Class<? extends IWriteConverter> writeConverter() default StringWriteConverter.class;

}
