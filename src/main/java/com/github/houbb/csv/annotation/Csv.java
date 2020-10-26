package com.github.houbb.csv.annotation;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.convert.read.CommonReadConverter;
import com.github.houbb.csv.support.convert.write.CommonWriteConverter;

import java.lang.annotation.*;

/**
 * CSV 注解
 * @author binbin.hou
 * @since 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.FIELD)
public @interface Csv {

    /**
     * 字段显示名称
     * 1. 默认使用 field.name
     * @return 显示名称
     */
    String label() default "";

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
     * 读取转换
     * @return 处理实现类
     */
    Class<? extends IReadConverter> readConverter() default CommonReadConverter.class;

    /**
     * 写入转换
     * @return 处理实现类
     */
    Class<? extends IWriteConverter> writeConverter() default CommonWriteConverter.class;

    /**
     * 读映射
     *
     * S:成功;F:失败
     * 枚举值用 ; 分割，映射用 : 分割。
     *
     * 优先级：相比较 readConverter 优先使用 readMapping，如果值不为空，且当前字段的值为 String，才进行处理。否则忽略。
     * 注意：必须要有 : 分割符号，否则报错。只对 String 类型字段生效。
     * @return 读
     * @since 0.1.0
     */
    String readMapping() default "";

    /**
     * 写映射
     *
     * 优先级：相比较 writeConverter 优先使用 writeMapping，如果值不为空，且当前字段的值为 String，才进行处理。否则忽略。
     * 注意：必须要有 : 分割符号，否则报错。只对 String 类型字段生效。
     * @return 映射结果
     * @since 0.1.0
     */
    String writeMapping() default "";

}
