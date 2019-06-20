# 支持内嵌对象

有时候我们希望像使用 mongo 一样，非常方便的存取 csv 的嵌套对象。

对于普通的 csv 都没有实现这个特性，本次做了一个尝试，支持内嵌对象的存取。

## 取舍

就像 csv 的简单，需要用到符号 `,` 一样。

内嵌对象为了不破坏 csv 的规范，使用了符号 `:`。

换言之，也就是对象内容中不能使用这个符号。

后期会针对出现的符号进行转义，避免这种冲突。

# 测试案例

所有测试代码可以参考 [test]() 模块。

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