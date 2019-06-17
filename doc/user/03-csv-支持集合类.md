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

# 注意

为了保证 csv 以 `,` 分隔的统一性。

集合使用 `|` 进行分隔，其中 map 的 key/value 分隔，用到了 `=`。

在使用时要注意，不要包含上述的符号，否则会出现解析错乱。