package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.User;
import com.github.houbb.csv.model.UserAnnotation;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
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
     * 基于注解的写入测试
     * @since 0.0.2
     */
    @Test
    public void annotationTest() {
        final String path = "src\\test\\resources\\annotation.csv";
        CsvWriteBs.newInstance(path)
                .write(buildAnnotationList());
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

    /**
     * 构建基于注解的测试列表
     * @return 列表
     */
    private List<UserAnnotation> buildAnnotationList() {
        UserAnnotation user = new UserAnnotation();
        user.name("你好")
                .password("123")
                .birthday(new Date());
        return Arrays.asList(user);
    }

}
