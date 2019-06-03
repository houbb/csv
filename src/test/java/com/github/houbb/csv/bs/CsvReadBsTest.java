package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.User;
import com.github.houbb.csv.model.UserAnnotation;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class CsvReadBsTest {

    @Test
    public void commonTest() {
        final String path = "src\\test\\resources\\common.csv";
        List<User> userList = CsvReadBs.newInstance(path)
                .read(User.class);
        System.out.println(userList);
    }

    /**
     * 基于注解
     * @since 0.0.2
     */
    @Test
    public void annotationTest() {
        final String path = "src\\test\\resources\\annotation.csv";
        List<UserAnnotation> userList = CsvReadBs.newInstance(path)
                .read(UserAnnotation.class);
        System.out.println(userList);
    }


}
