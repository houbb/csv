package com.github.houbb.csv.support.csv;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.api.ICsv;
import com.github.houbb.csv.api.IReadContext;
import com.github.houbb.csv.api.IWriteContext;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.constant.CsvOperateType;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.read.entry.EntryReadConverter;
import com.github.houbb.csv.support.convert.write.entry.EntryWriteConverter;
import com.github.houbb.csv.support.writer.ICsvWriter;
import com.github.houbb.csv.support.writer.impl.CsvWriterContext;
import com.github.houbb.csv.util.CsvFieldUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.heaven.util.util.Optional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 默认的 csv 处理实现
 *
 * @param <T> 泛型
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class DefaultCsv<T> implements ICsv<T> {

    @Override
    public List<String> write(IWriteContext<T> context) {
        final List<T> writeList = context.list();
        if (CollectionUtil.isEmpty(writeList)) {
            return Collections.emptyList();
        }
        final Optional<T> firstOpt = CollectionUtil.firstNotNullElem(writeList);
        if (firstOpt.isNotPresent()) {
            return Collections.emptyList();
        }
        final T elem = firstOpt.get();
        final List<FieldBean> fieldBeanList = CsvFieldUtil.getSortedFields(elem.getClass(),
                context.sort(), CsvOperateType.WRITE);
        if (CollectionUtil.isEmpty(fieldBeanList)) {
            return Collections.emptyList();
        }

        // 2.1 存储字符串列表
        final int size = context.list().size();
        List<String> list = Guavas.newArrayList(size);

        // 2.2 构建每一行的内容
        EntryWriteConverter entryWriteConverter = new EntryWriteConverter();
        SingleWriteContext singleWriteContext = SingleWriteContext.newInstance()
                .sort(context.sort())
                .escape(context.escape())
                .split(CsvConst.COMMA);
        for (T t : writeList) {
            singleWriteContext.element(t);

            final String writeLine = entryWriteConverter.convert(singleWriteContext);
            if (StringUtil.isEmpty(writeLine)) {
                continue;
            }
            list.add(writeLine);
        }

        // 2.3 处理字符串列表
        final ICsvWriter csvWriter = context.writer();
        final CsvWriterContext csvWriterContext = CsvWriterContext.newInstance()
                .writeBom(context.writeBom())
                .writeHead(context.writeHead())
                .list(list);

        // 2.4 写入头信息
        final boolean needWriteHead = context.writeHead();
        if (needWriteHead) {
            // 只有需要写入时，才进行计算。
            String headLine = buildWriteHead(fieldBeanList);
            csvWriterContext.head(headLine);
        }

        //2.5 执行写入
        csvWriter.write(csvWriterContext);

        //3. 返回结果列表
        return list;
    }

    /**
     * 构建表头
     * （1）指定注解，且 label 不为空，则使用 label
     * （2）使用 field.name()
     * <p>
     * 所有字段 label 通过逗号连接。
     *
     * @param fieldBeanList 元素列表
     * @return 对应的 head 字符串
     */
    private String buildWriteHead(List<FieldBean> fieldBeanList) {
        Collection<String> headList = Guavas.newArrayList(fieldBeanList.size());

        for (FieldBean bean : fieldBeanList) {
            String name = bean.name();
            Optional<Csv> csvOptional = bean.annotationOptByType(Csv.class);
            if (csvOptional.isPresent()) {
                String label = csvOptional.get().label();
                if (StringUtil.isNotEmpty(label)) {
                    name = label;
                }
            }

            headList.add(name);
        }
        return StringUtil.join(headList, PunctuationConst.COMMA);
    }

    @Override
    public List<T> read(IReadContext<T> context) {
        // 1. 构建字段和下标之间的映射关系。
        Map<Integer, FieldBean> readMapping = CsvFieldUtil.getReadMapping(context.readClass(), context.sort());
        if (MapUtil.isEmpty(readMapping)) {
            return Collections.emptyList();
        }

        //2. 文件内容
        List<String> allLines = context.reader().read();
        List<String> readableLines = CollectionUtil.subList(allLines,
                context.startIndex(), context.endIndex());
        if (CollectionUtil.isEmpty(readableLines)) {
            return Collections.emptyList();
        }

        //3. 映射处理
        List<T> list = Guavas.newArrayList(readableLines.size());
        final Class<T> readClass = context.readClass();
        final EntryReadConverter<T> readConverter = new EntryReadConverter<>();

        // 单行的上下文，使用同一个对象，节约内存及堆开销
        SingleReadContext singleReadContext = new SingleReadContext();
        singleReadContext
                .classType(readClass)
                .sort(context.sort())
                .split(CsvConst.COMMA)
                .escape(context.escape());
        for (String line : readableLines) {
            // 跳过空白行
            if (StringUtil.isEmpty(line)) {
                continue;
            }

            // 设置对象内容
            singleReadContext.value(line);
            T instance = readConverter.convert(singleReadContext);
            list.add(instance);
        }
        return list;
    }

}
