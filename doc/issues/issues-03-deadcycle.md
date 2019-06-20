# 如果一个类引用本身

```java
class User {
    List<User> children;
}
```

很可能会导致死循环。

验证并修复这个问题。

最好在 @since 0.0.6 版本。

# 对于特殊符号的转义。