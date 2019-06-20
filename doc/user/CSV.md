# CSV

基于 java 注解的 csv 读写框架。

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

## 变更日志

[CHANGE_LOG.md](https://github.com/houbb/csv/blob/master/doc/CHANGE_LOG.md)

## 开源地址

[csv](https://github.com/houbb/csv)

# 快速开始

## 环境

jdk7+

maven 3.x

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>csv</artifactId>
    <version>0.0.6</version>
</dependency>
```

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

# 引导类

## 为什么需要引导类

为了灵活的配置和默认配置并存，使用工具类会大大降低灵活性。

为了用户使用的便利性，和后期拓展的灵活性。

## 引导类

CSV 有两个引导类：

| 名称 | 作用 |
|:---|:---|
| CsvWriteBs | csv 文件写入引导类 |
| CsvReadBs | csv 文件读取引导类 |

## CsvWriteBs

| 方法 | 默认值 | 说明 |
|:---|:---|:---|
| newInstance(final String path) | `必填` | 创建实例，并且指定待写入文件路径。|
| path (final String path) | | 配置文件路径，只有重新指定 path 路径时需要调用。|
| writeHead(boolean writeBom) | `true` |是否写入 head 头，如果想指定名称，可以结合注解。只有无 head 信息时，会写入。 |
| writeBom(boolean writeBom) | `true` | 是否写入 UTF8 BOM 头，只有文件为空时才会写入。 |
| charset(String charset) | `UTF-8` | 指定文件编码 |
| sort(ISort sort) | NoSort | 默认不进行字段排序 |
| write(List<T> list) | `无` | 待写入的文件列表 |
| escape | false | 是否进行特殊字符的转换 |

## CsvReadBs

| 方法 | 默认值 | 说明 |
|:---|:---|:---|
| newInstance(final String path) | `必填` |创建实例，并且指定待读取文件路径。|
| path (final String path) | | 配置文件路径，只有重新指定 path 路径时需要调用。|
| charset(String charset) | `UTF-8` | 指定文件编码 |
| sort(ISort sort) | NoSort | 默认不进行字段排序 |
| startIndex(int startIndex) | 1 | 文件的第二行，默认第一行是 head |
| endIndex(int endIndex) |  | 文件的最后一行 |
| escape | false | 是否进行特殊字符的转换 |

# Csv 注解

## 注解属性说明

用于待处理对象的字段上。

```java
    /**
     * 字段显示名称
     * 1. 默认使用 field.name
     * @return 显示名称
     */
    String label() default "";

    /**
     * 读取是否需要
     * @return 是
     */
    boolean readRequire() default true;

    /**
     * 写入是否需要
     * @return 是
     */
    boolean writeRequire() default true;

    /**
     * 读取转换
     * @return 处理实现类
     */
    Class<? extends IReadConverter> readConverter() default CommonReadConverter.class;

    /**
     * 写入转换
     * @return 处理实现类
     */
    Class<? extends IWriteConverter> writeConverter() default StringWriteConverter.class;
```

## 属性概览表

| 属性 | 默认值 | 说明 |
|:---|:---|:---|
| label | 字段名称 | 用于 csv 头生成 |
| readRequire | true | 是否需要从 csv 文件读取 |
| writeRequire | true | 当前字段是否需要写入 csv 文件 |
| readConverter | CommonReadConverter | 将 csv 中的字符串转化为当前字段类型，支持 8 大基本类型+String |
| writeConverter | StringWriteConverter | 直接调用当前字段值 toString() 方法，null 直接为空字符串 |

其中 readConverter/writeConverter 支持用户自定义

# 字段注解

## 对象定义

```java
public class UserAnnotation {

    @Csv(label = "名称")
    private String name;

    @Csv(label = "密码", readRequire = false, writeRequire = false)
    private String password;

    @Csv(label = "生日", readConverter = ReadDateConvert.class, writeConverter = WriteDateConvert.class)
    private Date birthday;

    //Getter & Setter & toString()
}
```


### ReadDateConvert/WriteDateConvert

使我们自定义的针对 Date 的转换实现。

- Write

```java
public class WriteDateConvert implements IWriteConverter<Date> {

    @Override
    public String convert(Date value) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(value);
    }

}
```

- ReadDateConvert

```java
public class ReadDateConvert implements IReadConverter<Date> {

    @Override
    public Date convert(String value, Class fieldType) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
```

## 写入文件

```java
public void annotationTest() {
    final String path = "src\\test\\resources\\annotation.csv";
    CsvWriteBs.newInstance(path)
            .write(buildAnnotationList());
}
```

其中列表构建：

```java
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
```

- 生成文件内容

```
名称,生日
你好,20190603
```

## 读取文件测试

```java
public void annotationTest() {
     final String path = "src\\test\\resources\\annotation.csv";
     List<UserAnnotation> userList = CsvReadBs.newInstance(path)
             .read(UserAnnotation.class);
     System.out.println(userList);
}
```

- 日志信息

```
[UserAnnotation{name='你好', password='null', birthday=Mon Jun 03 00:00:00 CST 2019}]
```

# 集合类

有时候对象中会包含数组、Map、Collection 等常见集合。

为了存储的便利性，默认提供集合的相关支持。

特性和普通字段保持一致，如果指定注解转换，则以注解为准。

## 使用示例

- UserCollection.java

用于演示集合的对象

```java
public class UserCollection {

    private String[] arrays;

    private LinkedList<String> lists;

    private Map<String, String> maps;

    private Set<String> sets;

    //Getter/Setter/toString()
}
```

## 存储

- 待存储对象的构建

```java
/**
 * 构建基于集合的测试列表
 * @return 列表
 * @since 0.0.3
 */
private List<UserCollection> buildCollectionList() {
    UserCollection user = new UserCollection();
    String[] arrays = new String[]{"a", "b", "c"};
    LinkedList<String> lists = new LinkedList<>(Arrays.asList(arrays));
    Map<String, String> maps = new HashMap<>();
    maps.put("key", "value");
    maps.put("key2", "value2");
    Set<String> sets = new HashSet<>();
    sets.add("set1");
    sets.add("set2");

    user.setLists(lists);
    user.setArrays(arrays);
    user.setMaps(maps);
    user.setSets(sets);
    return Arrays.asList(user);
}
```

- 执行存储

```java
public void collectionTest() {
    final String path = "src\\test\\resources\\collection.csv";
    CsvWriteBs.newInstance(path)
            .write(buildCollectionList());
}
```

- 存储效果

```
﻿arrays,lists,maps,sets
a|b,a|b|c,key2=value2|key=value,set1|set2
```

## 读取

- 测试类

```java
public void collectionTest() {
    final String path = "src\\test\\resources\\collection.csv";
    List<UserCollection> userList = CsvReadBs.newInstance(path)
            .read(UserCollection.class);
    System.out.println(userList);
}
```

- 测试日志

```
[UserCollection{arrays=[a, b], lists=[a, b, c], maps={key=value, key2=value2}, sets=[set2, set1]}]
```

## 注意

为了保证 csv 以 `,` 分隔的统一性。

集合使用 `|` 进行分隔，其中 map 的 key/value 分隔，用到了 `=`。

在使用时要注意，不要包含上述的符号，否则会出现解析错乱。

ps: 如果确实用到这些字符，可以见后面的[特殊字符转义功能](https://github.com/houbb/csv/blob/master/doc/user/05-csv-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6%E8%BD%AC%E4%B9%89.md)。

# 支持内嵌对象

有时候我们希望像使用 mongoDB 一样，非常方便的存取 csv 的嵌套对象。

对于普通的 csv 都没有实现这个特性，本次做了一个尝试，支持内嵌对象的存取。

## 取舍

就像 csv 的简单，需要用到符号 `,` 一样。

内嵌对象为了不破坏 csv 的规范，使用了符号 `:`。

换言之，也就是对象内容中不能使用这个符号。

后期会针对出现的符号进行转义，避免这种冲突。

# 测试案例

## 示例对象

- UserEntry.java

```java
public class UserEntry {

    /**
     * 名称
     */
    private String name;

    /**
     * 内嵌的用户信息
     */
    @CsvEntry
    private User user;

    //Getter/Setter/ToString
}
```

这里在需要内嵌的对象上使用注解 `@CsvEntry` 表示需要进行内嵌的对象转换。

- User.java

其中 User 对象是原来使用的普通 java 对象

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

    //Getter/Setter/ToString
}
```

## 写入测试

```java
public void entryTest() {
    final String path = "src\\test\\resources\\entry.csv";
    CsvWriteBs.newInstance(path)
            .write(buildEntryList());
}
```

- buildEntryList()

负责对象构建代码，内容如下：

```java
/**
 * 用户明细列表
 * @return 明细列表
 * @since 0.0.5
 */
private List<UserEntry> buildEntryList() {
    UserEntry userEntry = new UserEntry();
    userEntry.name("test");
    userEntry.user(buildCommonList().get(0));
    return Collections.singletonList(userEntry);
}
```

- buildCommonList()

```java
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

生成文件效果

```
name,user
test,你好:10:60.0:200.0:true:4:1:Y:1
```

如你所见，这里内嵌对象的属性使用了 `:` 进行分隔。

## 读取测试

```java
public void entryTest() {
    final String path = "src\\test\\resources\\entry.csv";
    List<UserEntry> userList = CsvReadBs.newInstance(path)
            .read(UserEntry.class);
    System.out.println(userList);
}
```

输出信息

```
[UserEntry{name='test', user=User{name='你好', age=10, score=60.0, money=200.0, sex=true, level=4, id=1, status=Y, coin=1}}]
```

# 特殊字符转义

在实际使用中，有时候我们会用到 `,|:=`。

这几个被使用的特殊字符。

如果你希望这些特殊的字符被正确的存取，那么可以使用 `escape` 属性执行。

## 特殊字符的转换

| 原始 | 转义后 |
|:--|:--|
| `,` | `&CSV_COMMA;` |
| `|` | `&CSV_OR;` |
| `:` | `&CSV_COLON;`|
| `=` | `&CSV_EUQAL;` |

下面演示一下如何使用

暂时转义字符不支持自定义。

# 测试代码

## 写入测试

```java
public void escapeTest() {
    final String path = "src\\test\\resources\\escape.csv";
    CsvWriteBs.newInstance(path)
            .escape(true)
            .write(buildUserEscapeList());
}
```

- 生成文件效果

```
name,map,nameList,user
one&CSV_COMMA;one,key&CSV_EUQAL;key=value&CSV_EUQAL;value,one&CSV_OR;one|two&CSV_OR;two,entry&CSV_COLON;name:0:0.0:0.0:false:0:0: :0
```

### 相关代码

- UserEscape.java

其中用到的对象为：

```java
public class UserEscape {

    /**
     * 使用 ,
     */
    private String name;

    /**
     * 使用 map =
     */
    private Map<String, String> map;

    /**
     * 使用 |
     */
    private List<String> nameList;

    /**
     * 使用 :
     */
    @CsvEntry
    private User user;

    //Getter & Setter & ToString()
}
```

- buildUserEscapeList()

构建时，特意使用了特殊的字符。

```java
private List<UserEscape> buildUserEscapeList() {
    UserEscape escape = new UserEscape();
    Map<String, String> map = new HashMap<>();
    map.put("key=key", "value=value");
    User user = new User();
    user.name("entry:name");

    escape.name("one,one");
    escape.nameList(Arrays.asList("one|one", "two|two"));
    escape.map(map);
    escape.user(user);

    return Collections.singletonList(escape);
}
```

## 读取测试

```java
public void escapeTest() {
    final String path = "src\\test\\resources\\escape.csv";
    List<UserEscape> userList = CsvReadBs.newInstance(path)
            .escape(true)
            .read(UserEscape.class);
    System.out.println(userList);
}
```

- 日志信息

```
[UserEscape{name='one,one', nameList=[one|one, two|two], user=User{name='entry:name', age=0, score=0.0, money=0.0, sex=false, level=0, id=0, status= , coin=0}, map={key=key=value=value}}]
```

# 后续设计

## 更丰富的类型支持

支持更多的 java 常见类型。

## 更灵活的配置

比如支持用户自定义转义字符

支持文件的写入模式等等。

# 开源地址

[csv](https://github.com/houbb/csv)

可以查看相关代码。

为了便于其他人阅读和使用，代码拥有详细的注释。
