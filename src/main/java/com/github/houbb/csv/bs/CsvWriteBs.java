package com.github.houbb.csv.bs;

import com.github.houbb.csv.api.ICsv;
import com.github.houbb.csv.support.context.DefaultWriteContext;
import com.github.houbb.csv.support.csv.DefaultCsv;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.support.writer.impl.CsvWriterNone;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.sort.ISort;
import com.github.houbb.heaven.support.sort.impl.NoSort;
import com.github.houbb.heaven.util.common.ArgUtil;

import java.util.List;

/**
 * csv 写入引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class CsvWriteBs {

    /**
     * 是否写入 head 头信息
     * @since 0.0.1
     */
    private boolean writeHead = true;

    /**
     * csv 写入类操作
     *
     * 1. 默认什么都不做
     * @since 0.0.8
     */
    private ICsvWriter writer = Instances.singleton(CsvWriterNone.class);

    /**
     * 指定排序方式
     *
     * @since 0.0.8 废弃
     */
    @Deprecated
    private ISort sort = Instances.singleton(NoSort.class);

    /**
     * 特殊字符转换
     * @see com.github.houbb.csv.constant.CsvEscapeConst 特殊信息
     */
    private boolean escape = false;

    /**
     * 默认 csv 实现
     * @since 0.0.8
     */
    private ICsv csv = Instances.singleton(DefaultCsv.class);

    /**
     * 私有化构造器
     */
    private CsvWriteBs(){}

    /**
     * 新建对象实例
     * @return 实现
     * @since 0.0.8
     */
    public static CsvWriteBs newInstance() {
        return new CsvWriteBs();
    }

    /**
     * 是否写入表头
     * @param writeHead 是否写入表头
     * @return 实现
     * @since 0.0.8
     */
    public CsvWriteBs writeHead(boolean writeHead) {
        this.writeHead = writeHead;
        return this;
    }

    /**
     * 排序实现
     * @param sort 排序实现
     * @return this
     * @since 0.0.8
     */
    public CsvWriteBs sort(ISort sort) {
        ArgUtil.notNull(sort, "sort");

        this.sort = sort;
        return this;
    }

    /**
     * 是否进行转义
     * @param escape 是否
     * @return 实现
     * @since 0.0.8
     */
    public CsvWriteBs escape(boolean escape) {
        this.escape = escape;
        return this;
    }

    /**
     * 设置写入实现类
     * @param writer 写入类
     * @return this
     * @since 0.0.8
     */
    public CsvWriteBs writer(ICsvWriter writer) {
        ArgUtil.notNull(writer, "writer");

        this.writer = writer;
        return this;
    }

    /**
     * 将指定列表的内容写入到文件中
     * @param list 列表
     * @param <T> 泛型
     * @return 写入的字符串列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<String> write(List<T> list) {
        DefaultWriteContext<T> context = new DefaultWriteContext<>();
        context.list(list)
                .writeHead(writeHead)
                .sort(sort)
                .escape(escape)
                .writer(writer)
                ;

        return csv.write(context);
    }

}
