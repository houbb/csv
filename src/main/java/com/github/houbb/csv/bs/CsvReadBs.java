package com.github.houbb.csv.bs;

import com.github.houbb.csv.api.ICsv;
import com.github.houbb.csv.constant.CsvConfigConst;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.support.context.DefaultReadContext;
import com.github.houbb.csv.support.csv.DefaultCsv;
import com.github.houbb.csv.support.csv.DefaultStringListCsv;
import com.github.houbb.csv.support.reader.ICsvReader;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.sort.ISort;
import com.github.houbb.heaven.support.sort.impl.NoSort;
import com.github.houbb.heaven.util.common.ArgUtil;

import java.util.List;

/**
 * csv 读取引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class CsvReadBs {

    /**
     * 读取类实现
     * @since 0.0.8
     */
    private ICsvReader reader;

    /**
     * 开始下标
     * 1. 跳过第一行的 head
     */
    private int startIndex = CsvConst.DEFAULT_START_INDEX;

    /**
     * 结束下标
     */
    private int endIndex = CsvConst.DEFAULT_END_INDEX;

    /**
     * 执行转意
     * @since 0.0.6
     */
    private boolean escape = false;

    /**
     * 引用字符
     * @since 0.2.0
     */
    private char quoteChar = CsvConfigConst.DEFAULT_QUOTE_CHAR;

    /**
     * 无任何排序实现
     * @since 0.0.8
     */
    @Deprecated
    private ISort sort = Instances.singleton(NoSort.class);

    /**
     * csv 默认实现
     *
     * @since 0.0.8
     */
    private final ICsv csv = Instances.singleton(DefaultCsv.class);

    /**
     * 私有化构造器
     */
    private CsvReadBs(){}

    /**
     * 新建对象
     * @return this
     * @since 0.0.1
     */
    public static CsvReadBs newInstance() {
        return new CsvReadBs();
    }

    /**
     * 读取类
     * @param reader 读取类
     * @return this
     * @since 0.0.8
     */
    public CsvReadBs reader(ICsvReader reader) {
        ArgUtil.notNull(reader, "reader");

        this.reader = reader;
        return this;
    }

    /**
     * 设置开始下标
     * @param startIndex 开始下标
     * @return this
     * @since 0.0.8
     */
    public CsvReadBs startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    /**
     * 设置结束下标
     * @param endIndex 结束下标
     * @return this
     * @since 0.0.8
     */
    public CsvReadBs endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    /**
     * 设置是否进行转义
     * @param escape 结束下标
     * @return this
     * @since 0.0.8
     */
    public CsvReadBs escape(boolean escape) {
        this.escape = escape;
        return this;
    }

    public CsvReadBs quoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
        return this;
    }

    /**
     * 将指定文件的内容读取到列表中
     * @param tClass 类型
     * @param <T> 泛型
     * @return 列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> read(Class<T> tClass) {
        DefaultReadContext<T> context = new DefaultReadContext<>();
        context.reader(reader)
                .startIndex(startIndex)
                .endIndex(endIndex)
                .sort(sort)
                .readClass(tClass)
                .escape(escape)
                .quoteChar(quoteChar)
                ;

        return csv.read(context);
    }

    /**
     * 将指定文件的内容读取到列表中
     * @return 列表
     * @since 0.2.0
     */
    @SuppressWarnings("unchecked")
    public List<List<String>> readStringList() {
        DefaultReadContext<List<String>> context = new DefaultReadContext<>();
        context.reader(reader)
                .startIndex(startIndex)
                .endIndex(endIndex)
                .sort(sort)
                .escape(escape)
                .quoteChar(quoteChar)
        ;

        ICsv<List<String>> csv = new DefaultStringListCsv();
        return csv.read(context);
    }

}
