package com.github.houbb.csv.constant;

/**
 * CSV 特殊符号转换常量
 *
 * 采用类似于 html 的特殊字符转移。
 * 用途：
 * （1）存储的时候，对字段属性进行统一替换
 * （2）读取的时候，对字段属性进行还原。
 *
 * 实现方式：
 * （1）代码写死，简单实现。（本期使用这种方式实现）
 * （2）更加灵活的方式，添加一层 readFilter writeFilter
 * 后期可以便于用户自行定义。
 * @author binbin.hou
 * @since 0.0.6
 */
public final class CsvEscapeConst {

    private CsvEscapeConst(){}

    /**
     * 逗号
     */
    public static final String COMMA = "&CSV_COMMA;";

    /**
     * OR
     */
    public static final String OR = "&CSV_OR;";

    /**
     * 等于
     */
    public static final String EQUAL = "&CSV_EUQAL;";

    /**
     * 冒号
     */
    public static final String COLON = "&CSV_COLON;";

}
