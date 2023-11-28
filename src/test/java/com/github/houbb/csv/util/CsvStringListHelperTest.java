package com.github.houbb.csv.util;

import com.github.houbb.csv.model.User;
import com.github.houbb.csv.model.UserMapping;
import com.github.houbb.csv.support.writer.impl.CsvWriters;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@Ignore
public class CsvStringListHelperTest {

    /**
     * 读取列表
     * @since 0.0.8
     */
    @Test
    public void readTest() {
        final String path = "D:\\code\\github\\csv\\src\\test\\resources\\stringlist.csv";
        final String target = "D:\\code\\github\\csv\\src\\test\\resources\\stringlist2.csv";

        List<List<String>> dataList = CsvStringListHelper.read(path);
        System.out.println(dataList);

        CsvStringListHelper.write(target, dataList);
    }

}
