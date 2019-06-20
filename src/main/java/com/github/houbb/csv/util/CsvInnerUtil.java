package com.github.houbb.csv.util;

import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.heaven.util.lang.CharUtil;

/**
 * 内部工具类
 * @author binbin.hou
 * @since 0.0.5
 */
public final class CsvInnerUtil {

    private CsvInnerUtil(){}

    /**
     * 获取下一个分隔符号
     * @param preSplit 原来的分隔符号
     * @return 下一个分隔符
     */
    public static String getNextSplit(final String preSplit) {
        if(CsvConst.COMMA.equals(preSplit)) {
            return CsvConst.ENTRY_SPLIT_UNIT;
        }
        if(preSplit.startsWith(CsvConst.ENTRY_SPLIT_UNIT)) {
            final int times = preSplit.length()+1;
            return CharUtil.repeat(CsvConst.ENTRY_SPLIT_UNIT_CHAR, times);
        }
        throw new UnsupportedOperationException("暂时不支持的分隔符!");
    }

}
