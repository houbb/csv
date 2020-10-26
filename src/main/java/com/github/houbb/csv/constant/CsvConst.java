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

    /**
     * 默认开始下标
     * @since 0.0.8
     */
    public static final int DEFAULT_START_INDEX = 1;

    /**
     * 默认结束下标
     * @since 0.0.8
     */
    public static final int DEFAULT_END_INDEX = Integer.MAX_VALUE;

    /**
     * 映射分割符号
     * @since 0.1.0
     */
    public static final String MAPPING_SPLITTER = ";";

    /**
     * 每一组元素的分割符号
     * @since 0.1.0
     */
    public static final String MAPPING_UNIT_SPLITTER = ":";

}
