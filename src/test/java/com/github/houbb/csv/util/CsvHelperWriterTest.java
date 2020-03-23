package com.github.houbb.csv.util;

import com.github.houbb.csv.model.User;
import com.github.houbb.csv.support.reader.impl.CsvReaders;
import com.github.houbb.csv.support.writer.impl.CsvWriters;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.8
 */
@Ignore
public class CsvHelperWriterTest {

    /**
     * 读取列表
     * @since 0.0.8
     */
    @Test
    public void writerTest() {
        List<User> userList = buildCommonList();

        CsvHelper.write(userList);
        CsvHelper.write(userList, CsvWriters.console());
    }

    @Test
    public void writerFileTest() {
        final String path = "src\\test\\resources\\helper.csv";

        List<User> userList = buildCommonList();
        CsvHelper.write(userList, CsvWriters.filePath(path));
    }

    /**
     * 构建通用测试列表
     * @return 列表
     */
    private List<User> buildCommonList() {
        User user = new User();
        short s = 4;
        byte b = 1;
        user.age(10)
                .name("你好")
                .id(1L)
                .score(60)
                .coin(b)
                .level(s)
                .money(200)
                .sex(true)
                .status('Y');
        return Arrays.asList(user);
    }

}
