package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class CsvWriteBsTest {

    @Test
    @Ignore
    public void commonTest() {
        final String path = "src\\test\\resources\\common.csv";
        CsvWriteBs.newInstance(path)
                .write(buildCommonList());
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
