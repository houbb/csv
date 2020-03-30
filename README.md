# csv

[CSV](https://github.com/houbb/csv) 是基于 java 注解的 csv 读写框架，让你更加优雅方便的操作 csv。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/csv/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/csv)
[![Build Status](https://www.travis-ci.org/houbb/csv.svg?branch=master)](https://www.travis-ci.org/houbb/csv?branch=master)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/csv/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/csv)

## 相关框架

[Apache commons-csv](https://github.com/apache/commons-csv)

[super-csv](https://github.com/super-csv/super-csv)

简单看了下，这两个框架提供的特性都非常的基础。

## 创作原由

以前觉得 csv 文件的读写非常简单，就懒得封装。

最近一个月写了两次 csv 文件相关的东西，发现要处理的细节还是有的，还浪费比较多的时间。

比如：

1. UTF-8 中文编码使用 excel 打开乱码，因为缺少 BOM 头。

2. 不同类型字段转化为字符串，顺序的指定，head 头的指定，如果手写都会很繁琐。

3. 读取的时候最后 `,` 后无元素，split 会缺失等。

为了解决上述问题，此框架应运而生。

## 特性

- Fluent 流式写法

- 基于 java 注解，支持自定义的转换和灵活配置

- 内置 8 大基本类型以及 String 类型转换

- 解决 Excel 直接打开，utf-8 乱码问题

- 支持集合、数组、Map 的存取

- 支持对象中内嵌其他对象

- 支持特殊字符转义

### v0.0.9 变更

- write() 写入文件方式调整

多次写入相同文件时，默认使用 APPEND 的方式对内容进行追加。

# 快速开始

## 环境

jdk7+

maven 3.x

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>csv</artifactId>
    <version>0.0.9</version>
</dependency>
```

## 示例代码

### 写入

详情参考 [CsvHelperWriterTest.java](https://github.com/houbb/csv/blob/release_0.0.8/src/test/java/com/github/houbb/csv/util/CsvHelperWriterTest.java)

```java
final String path = "src\\test\\resources\\helper.csv";

CsvHelper.write(buildCommonList(), CsvWriters.filePath(path));
```

- 文件生成

```csv
name,age,score,money,sex,level,id,status,coin
你好,10,60.0,200.0,true,4,1,Y,1
```

### 读取

详情参考 [CsvHelperReaderTest.java](https://github.com/houbb/csv/blob/release_0.0.8/src/test/java/com/github/houbb/csv/util/CsvHelperReaderTest.java)

- 读取文件

```java
final String path = "src\\test\\resources\\common.csv";

List<User> userList = CsvHelper.read(path, User.class);
Assert.assertEquals("[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]", userList.toString());
```

- 读取字符串列表

也支持直接读取字符串列表。

```java
List<String> lines = Arrays.asList("name,age,score,money,sex,level,id,status,coin",
                "你好,10,60.0,200.0,true,4,1,Y,1");

List<User> userList = CsvHelper.read(lines, User.class);
Assert.assertEquals("[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]", userList.toString());
```

### 对象信息

其中使用的属性如下：

- User.java

演示基本类型的转换

```java
public class User {

    private String name;

    private int age;

    private float score;

    private double money;

    private boolean sex;

    private short level;

    private long id;

    private char status;

    private byte coin;

    //Getter & Setter & toString()
}
```

- 对象列表构建

```java
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
```

# 拓展阅读

[01-CSV 引导类方法说明](doc/user/01-csv-引导类.md)

[02-CSV 字段注解的使用](doc/user/02-csv-注解使用.md)

[03-CSV 集合相关支持](doc/user/03-csv-支持集合类.md)

[04-CSV 内嵌对象使用](doc/user/04-csv-支持内嵌对象.md)

[05-CSV 内嵌对象使用](doc/user/05-csv-特殊字符转义.md)

# 后期 road-map

- 引入 `@Order` 注解或者新增 `order()` 注解属性

避免 java 反射存在的字段顺序和声明顺序不一致问题

- 使用 converter 项目统一优化

读写转换等操作统一复用。