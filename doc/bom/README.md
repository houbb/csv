# BOM

—— Byte Order Mark，中文名译作“字节顺序标记”。

在这里找到一段关于 BOM 的说明：

在UCS 编码中有一个叫做 "Zero Width No-Break Space" ，中文译名作“零宽无间断间隔”的字符，它的编码是 FEFF。

而 FFFE 在 UCS 中是不存在的字符，所以不应该出现在实际传输中。

UCS 规范建议我们在传输字节流前，先传输字符 "Zero Width No-Break Space"。

这样如果接收者收到 FEFF，就表明这个字节流是 Big-Endian 的；如果收到FFFE，就表明这个字节流是 Little- Endian 的。因此字符 "Zero Width No-Break Space" （“零宽无间断间隔”）又被称作 BOM。

UTF-8 不需要 BOM 来表明字节顺序，但可以用 BOM 来表明编码方式。

字符 "Zero Width No-Break Space" 的 UTF-8 编码是 EF BB BF。

所以如果接收者收到以 EF BB BF 开头的字节流，就知道这是 UTF-8编码了。

Windows 就是使用 BOM 来标记文本文件的编码方式的。

字符U+FEFF如果出现在字节流的开头，则用来标识该字节流的字节序，是高位在前还是低位在前。

如果它出现在字节流的中间，则表达零宽度非换行空格的意义，用户看起来就是一个空格。

从Unicode3.2开始，U+FEFF只能出现在字节流的开头，只能用于标识字节序，就如它的名称——字节序标记——所表示的一样；除此以外的用法已被舍弃。取而代之的是，使用U+2060来表达零宽度无断空白。

类似WINDOWS自带的记事本等软件，在保存一个以UTF-8编码的文件时，会在文件开始的地方插入三个不可见的字符（0xEF 0xBB 0xBF，即BOM）。

它是一串隐藏的字符，用于让记事本等编辑器识别这个文件是否以UTF-8编码。

对于一般的文件，这样并不会产生什么麻烦。

但对于 PHP来说，BOM是个大麻烦。

PHP并不会忽略BOM，所以在读取、包含或者引用这些文件时，会把BOM作为该文件开头正文的一部分。

根据嵌入式语言的特点，这串字符将被直接执行（显示）出来。

由此造成即使页面的 top padding 设置为0，也无法让整个网页紧贴浏览器顶部，因为在html一开头有这3个字符呢！


# 不同编码的对应 bom

```

```

# 参考资料

[中文乱码详解](https://www.cnblogs.com/lzmrex/p/9832086.html)

[大端小端,ascii,unicode,utf8,utf16,utf32,gb2312,gbk,gb18030等字符编码问题](https://blog.csdn.net/ztsinghua/article/details/44277833)

[常见编码和编码头BOM](https://www.cnblogs.com/signheart/p/c3b1000186199e89d4e02c33f39ed418.html)