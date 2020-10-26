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

    /**
     * 读映射
     *
     * S:成功;F:失败
     * 枚举值用 ; 分割，映射用 : 分割。
     *
     * 优先级：相比较 readConverter 优先使用 readMapping，如果值不为空，且当前字段的值为 String，才进行处理。否则忽略。
     * 注意：必须要有 : 分割符号，否则报错。只对 String 类型字段生效。
     * @return 读
     * @since 0.1.0
     */
    String readMapping() default "";

    /**
     * 写映射
     *
     * 优先级：相比较 writeConverter 优先使用 writeMapping，如果值不为空，且当前字段的值为 String，才进行处理。否则忽略。
     * 注意：必须要有 : 分割符号，否则报错。只对 String 类型字段生效。
     * @return 映射结果
     * @since 0.1.0
     */
    String writeMapping() default "";
```

## 属性概览表

| 属性 | 默认值 | 说明 |
|:---|:---|:---|
| label | 字段名称 | 用于 csv 头生成 |
| readRequire | true | 是否需要从 csv 文件读取 |
| writeRequire | true | 当前字段是否需要写入 csv 文件 |
| readConverter | CommonReadConverter | 将 csv 中的字符串转化为当前字段类型，支持 8 大基本类型+String |
| writeConverter | StringWriteConverter | 直接调用当前字段值 toString() 方法，null 直接为空字符串 |
| readMapping | "" | 读取时的枚举值映射 |
| writeMapping | "" | 写入时的枚举值映射 |

其中 readConverter/writeConverter 支持用户自定义


# 使用代码示例

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

# 枚举映射

## 对象定义

```java
public class UserMapping {

    @Csv(readMapping = "S:成功;F:失败", writeMapping = "S:成功;F:失败")
    private String status;
    
    //getter/setter/toString()

}
```

这种方法适合快速的枚举导出，如果比较复杂，可以使用对应的 converter 即可。

## 写入

```java
UserMapping userMapping = new UserMapping();
userMapping.status("S");

CsvHelper.write(Collections.singletonList(userMapping), CsvWriters.filePath("src\\test\\resources\\mapping.csv"));
```

输出文件效果

```
status
成功
```

枚举值 S 被映射成了成功。

## 读取

有写入，就有对应的读取。

```java
final String path = "src\\test\\resources\\mapping.csv";

List<UserMapping> userList = CsvHelper.read(path, UserMapping.class);
Assert.assertEquals("[UserMapping{status='S'}]", userList.toString());
```

可以把成功映射为 S，便于一些枚举值的映射处理和存储。
