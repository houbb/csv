package com.github.houbb.csv.constant;

/**
 * CSV 常量定义
 * @author binbin.hou
 * @since 0.0.4
 */
public final class CsvConst {

    private CsvConst(){}

    /**
     * 逗号
     * 用于 CSV 标准的分隔字段信息。
     */
    public static final String COMMA = ",";

    /**
     * 数组分隔符 or
     */
    public static final String OR = "|";

    /**
     * map 的 key=value 会使用
     */
    public static final String EQUALS = "=";

    /**
     * 拆分数组（or）
     */
    public static final String SPLIT_OR = "\\|";

    /**
     * 明细分割单元-char
     * @since 0.0.5
     */
    public static final char ENTRY_SPLIT_UNIT_CHAR = ':';

    /**
     * 明细分割单元-char
     * @since 0.0.5
     */
    public static final String ENTRY_SPLIT_UNIT = ":";

}
