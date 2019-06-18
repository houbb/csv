package com.github.houbb.csv.support.csv;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.api.ICsv;
import com.github.houbb.csv.api.IReadContext;
import com.github.houbb.csv.api.IWriteContext;
import com.github.houbb.csv.constant.CsvOperateType;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.read.entry.EntryReadConverter;
import com.github.houbb.csv.support.convert.write.entry.EntryWriteConverter;
import com.github.houbb.csv.util.CsvFieldUtil;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.nio.PathUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.heaven.util.util.Optional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
public class DefaultCsv<T> implements ICsv<T> {

    /**
     * UTF_BOM 行信息，避免 excel 打开乱码
     */
    private static final byte[] UTF_BOM_BYTES = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    @Override
    public void write(IWriteContext<T> context) {
        final List<T> writeList = context.list();
        if (CollectionUtil.isEmpty(writeList)) {
            return;
        }
        final Optional<T> firstOpt = CollectionUtil.firstNotNullElem(writeList);
        if (firstOpt.isNotPresent()) {
            return;
        }
        final T elem = firstOpt.get();
        final List<FieldBean> fieldBeanList = CsvFieldUtil.getSortedFields(elem.getClass(),
                context.sort(), CsvOperateType.WRITE);
        if (CollectionUtil.isEmpty(fieldBeanList)) {
            return;
        }

        // 默认额外+1行，用于存储头信息
        final int size = context.list().size() + 1;
        List<String> list = Guavas.newArrayList(size);

        // 1.2 处理头信息
        if (context.writeHead()) {
            String headLine = buildWriteHead(fieldBeanList);
            list.add(headLine);
        }

        // 1.3 构建每一行的内容
        EntryWriteConverter<T> entryWriteConverter = new EntryWriteConverter<>();
        SingleWriteContext<T> singleWriteContext = new SingleWriteContext<>();
        singleWriteContext.sort(context.sort());
        for (T t : writeList) {
            singleWriteContext.element(t);
            final String writeLine = entryWriteConverter.convert(singleWriteContext);
            if (StringUtil.isEmpty(writeLine)) {
                continue;
            }
            list.add(writeLine);
        }

        try {
            //2. 统一写入文件
            // 默认使用添加的方式写入，后期再开放这个开关
            Path path = Paths.get(context.path());
            //2.1 创建父类文件夹
            File parent = path.getParent().toFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }

            //2.2 创建文件
            File file = path.toFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            //2.3 写入
            // 2.3.1 写入 bom 头(只需要第一次写入即可)
            if (context.writeBom()) {
                Files.write(path, UTF_BOM_BYTES, StandardOpenOption.APPEND);
            }
            // 2.3.2 写入实际内容
            Files.write(path, list, Charset.forName(context.charset()), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CommonRuntimeException(e);
        }
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
        List<String> readableLines = PathUtil.readAllLines(context.path(), context.charset(),
                context.startIndex(), context.endIndex());
        if (CollectionUtil.isEmpty(readableLines)) {
            return Collections.emptyList();
        }

        //3. 映射处理
        List<T> list = Guavas.newArrayList(readableLines.size());
        final Class<T> readClass = context.readClass();
        final EntryReadConverter<T> readConverter = new EntryReadConverter<>();
        for (String line : readableLines) {
            // 跳过空白行
            if (StringUtil.isEmpty(line)) {
                continue;
            }

            // 设置对象内容
            SingleReadContext<T> singleReadContext = new SingleReadContext<>();
            singleReadContext.classType(readClass);
            singleReadContext.sort(context.sort());
            singleReadContext.value(line);

            T instance = readConverter.convert(singleReadContext);
            list.add(instance);
        }
        return list;
    }

}
