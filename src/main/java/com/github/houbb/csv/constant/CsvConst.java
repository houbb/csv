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
     * 对象左侧符号
     * （1）存储
     * 单个对象：{}
     * 列表对象：{} | {} | {}
     * 保证列表语意的一致性。
     * （2）解析
     * 简单方式：通过字符串的截取
     * 优雅方式：通过 stack
     *
     * 解析原则，只有当当前字段满足字段时，才进行 {} 的特殊处理。
     */
    public static final String OBJECT_LEFT = "{";

    /**
     * 对象右侧符号
     */
    public static final String OBJECT_RIGHT = "}";

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
