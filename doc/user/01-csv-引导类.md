# CsvBs-csv 引导类

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
