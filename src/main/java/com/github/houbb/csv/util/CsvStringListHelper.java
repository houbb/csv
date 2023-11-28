package com.github.houbb.csv.util;

import com.github.houbb.csv.bs.CsvReadBs;
import com.github.houbb.csv.bs.CsvWriteBs;
import com.github.houbb.csv.constant.CsvConfigConst;
import com.github.houbb.csv.support.reader.impl.CsvReaders;
import com.github.houbb.csv.support.writer.impl.CsvWriters;

import java.util.List;

/**
 * csv 读写工具类
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public final class CsvStringListHelper {

    public static List<List<String>> read(final String filePath, final int startIx, final int endIx, final char quoteChar) {
        return CsvReadBs.newInstance().reader(CsvReaders.filePath(filePath))
                .startIndex(startIx).endIndex(endIx)
                .quoteChar(quoteChar).readStringList();
    }

    public static List<List<String>> read(final String filePath, final int startIx, final int endIx) {
        return read(filePath, startIx, endIx, CsvConfigConst.DEFAULT_QUOTE_CHAR);
    }

    public static List<List<String>> read(final String filePath, final int startIx) {
        return read(filePath, startIx, Integer.MAX_VALUE);
    }

    public static List<List<String>> read(final String filePath) {
        return read(filePath, 0);
    }

    public static void write(final String filePath, List<List<String>> dataList, final char quoteChar) {
        CsvWriteBs.newInstance()
                .writer(CsvWriters.filePath(filePath))
                .quoteChar(quoteChar)
                .writeStringList(dataList);
    }

    public static void write(final String filePath, List<List<String>> dataList) {
        write(filePath, dataList, CsvConfigConst.DEFAULT_QUOTE_CHAR);
    }

}
