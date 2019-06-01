# csv

基于 java 注解生成加签验签 csv。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/csv/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/csv)
[![Build Status](https://www.travis-ci.org/houbb/csv.svg?branch=master)](https://www.travis-ci.org/houbb/csv?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/csv/badge.svg?branch=master)](https://coveralls.io/github/houbb/csv?branch=master)

## 创作原由

以前觉得 csv 文件的多写非常简单，就懒得封装。

最近一个月写了两次 csv 文件相关的东西，发现要处理的细节还是有的，还浪费比较多的时间。

于是自己封装一套，便于以后使用。

## 特性

- Fluent 流式写法

- 基于 java annotation

- 字段类型转换的灵活支持，内置 8 大基本类型以及 String 类型转换

# 快速开始

## 环境

jdk7+

maven 3.x

## 示例代码

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

### 写入

- 测试代码

```java
public void commonTest() {
    final String path = "src\\test\\resources\\common.csv";
    CsvWriteBs.newInstance(path)
            .write(buildCommonList());
}
```

- 文件生成

```csv
name,age,score,money,sex,level,id,status,coin
你好,10,60.0,200.0,true,4,1,Y,1
```

### 读取

```java
public void commonTest() {
    final String path = "src\\test\\resources\\common.csv";
    List<User> userList = CsvReadBs.newInstance(path)
            .read(User.class);
    System.out.println(userList);
}
```

- 日志信息

```
[User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}]
```
