package com.github.houbb.csv.annotation;

import java.lang.annotation.*;

/**
 * CSV 内嵌类注解
 * （1）放在对象字段，或者对象列表字段上。
 * （2）如果放在 map 上，或者放在非对象字段上。则直接忽略。
 * （3）单独使用时，会采用内嵌的模式。
 * （4）和 {@link Csv} 共同使用时，优先以注解指定的为准。
 * 默认的就是 {@link com.github.houbb.csv.support.convert.read.CommonReadConverter} 和
 * {@link com.github.houbb.csv.support.convert.write.CommonWriteConverter}
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD})
public @interface CsvEntry {
}
