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

