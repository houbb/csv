package com.github.houbb.csv.bs;

import com.github.houbb.csv.model.*;
import com.github.houbb.csv.support.reader.impl.CsvReaderFilePath;
import com.github.houbb.csv.support.reader.impl.CsvReaders;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class CsvReadBsTest {

    @Test
    public void commonTest() {
        final String path = "src\\test\\resources\\common.csv";
        List<User> userList = CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
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
        List<UserAnnotation> userList = CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
                .read(UserAnnotation.class);
        Assert.assertEquals("你好", userList.get(0).name());
    }

    /**
     * 集合特性
     * @since 0.0.3
     */
    @Test
    public void collectionTest() {
        final String path = "src\\test\\resources\\collection.csv";
        List<UserCollection> userList =  CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
                .read(UserCollection.class);
        final String result = "[UserCollection{arrays=[a, b], lists=[a, b, c], maps={key=value, key2=value2}, sets=[set2, set1]}]";
        System.out.println(userList);
    }

    /**
     * 对象明细特性
     * @since 0.0.5
     */
    @Test
    public void entryTest() {
        final String path = "src\\test\\resources\\entry.csv";
        List<UserEntry> userList =  CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
                .read(UserEntry.class);
        final String string = "[UserEntry{name='test', user=User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}}]";
        Assert.assertEquals(string, userList.toString());
    }

    /**
     * 特殊字符转义
     * @since 0.0.6
     */
    @Test
    public void escapeTest() {
        final String path = "src\\test\\resources\\escape.csv";
        List<UserEscape> userList =  CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
                .escape(true)
                .read(UserEscape.class);
        final String string = "[UserEscape{name='one,one', nameList=[one|one, two|two], user=User{name='entry:name', age=0, score=0.0, money=0.0, sex=false, level=0, id=0, status=, coin=0}, map={key=key=value=value}}]";
        Assert.assertEquals("one,one", userList.get(0).name());
        System.out.println(userList);
    }

    /**
     * 自引用测试
     * @since 0.0.7
     */
    @Test
    public void selfRefTest() {
        final String path = "src\\test\\resources\\selfRef.csv";
        List<UserSelfRef> userList =  CsvReadBs.newInstance()
                .reader(CsvReaders.filePath(path))
                .read(UserSelfRef.class);
        System.out.println(userList);
    }

}
