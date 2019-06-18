package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.User;
import com.github.houbb.csv.model.UserAnnotation;
import com.github.houbb.csv.model.UserCollection;
import org.junit.Assert;
import org.junit.Test;

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

        final String result = "[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]";
        Assert.assertEquals(result, userList.toString());
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

        final String result = "[UserAnnotation{name='你好', password='null', birthday=Mon Jun 03 00:00:00 CST 2019}]";
        Assert.assertEquals(result, userList.toString());
    }

    /**
     * 集合特性
     * @since 0.0.3
     */
    @Test
    public void collectionTest() {
        final String path = "src\\test\\resources\\collection.csv";
        List<UserCollection> userList = CsvReadBs.newInstance(path)
                .read(UserCollection.class);
        final String result = "[UserCollection{arrays=[a, b], lists=[a, b, c], maps={key=value, key2=value2}, sets=[set2, set1]}]";
        Assert.assertEquals(result, userList.toString());
    }

}
