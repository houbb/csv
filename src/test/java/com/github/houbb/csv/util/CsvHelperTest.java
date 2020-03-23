package com.github.houbb.csv.util;

import com.github.houbb.csv.model.User;
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
public class CsvHelperTest {

    /**
     * 读取列表
     * @since 0.0.8
     */
    @Test
    public void readListTest() {
        List<String> lines = Arrays.asList("name,age,score,money,sex,level,id,status,coin",
                "你好,10,60.0,200.0,true,4,1,Y,1");

        List<User> userList = CsvHelper.read(lines, User.class);
        Assert.assertEquals("[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]", userList.toString());
    }

    /**
     * 读取列表
     * @since 0.0.8
     */
    @Test
    public void readFilePathTest() {
        final String path = "src\\test\\resources\\common.csv";

        List<User> userList = CsvHelper.read(path, User.class);
        Assert.assertEquals("[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]", userList.toString());
    }

}
