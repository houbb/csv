package com.github.houbb.csv.support.csv;

import com.github.houbb.csv.annotation.CSV;
import com.github.houbb.csv.api.*;
import com.github.houbb.csv.constant.CsvOperType;
import com.github.houbb.csv.support.convert.write.StringWriteConverter;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.support.sort.ISort;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.nio.PathUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.heaven.util.util.Optional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认的 csv 处理实现
 * @author binbin.hou
 * @since 1.0.0
 */
public class DefaultCsv<T> implements ICsv<T> {

    /**
     * UTF_BOM 行信息，避免 excel 打开乱码
     */
    private static final String UTF_BOM_LINE = new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF });

    @Override
    public void write(IWriteContext<T> context) {
        final List<T> writeList = context.list();
        if(CollectionUtil.isEmpty(writeList)) {
            return;
        }
        final Optional<T> firstOpt = CollectionUtil.firstNotNullElem(writeList);
        if(!firstOpt.isNotPresent()) {
            return;
        }
        final T elem = firstOpt.get();
        final List<FieldBean> fieldBeanList = getSortedFields(elem.getClass(),
                context.sort(), CsvOperType.WRITE);
        if (CollectionUtil.isEmpty(fieldBeanList)) {
            return;
        }

        // 默认额外+2行，用于存储 bom 和头信息
        final int size = context.list().size()+2;
        List<String> list = Guavas.newArrayList(size);

        // 1.1 写入 bom 头(只需要第一次写入即可)
        if(context.writeBom()) {
            list.add(UTF_BOM_LINE);
        }
        // 1.2 处理头信息
        if(context.writeHead()) {
            String headLine = buildWriteHead(fieldBeanList);
            list.add(headLine);
        }

        // 1.3 构建每一行的内容
        for(T t : writeList) {
            Optional<String> writeLine = buildWriteLine(t, fieldBeanList);
            if(writeLine.isNotPresent()) {
                continue;
            }
            list.add(writeLine.get());
        }

        try {
            //2. 统一写入文件
            // 默认使用添加的方式写入，后期再开放这个开关
            Path path = Paths.get(context.path());
            Files.write(path, list, Charset.forName(context.charset()), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CommonRuntimeException(e);
        }
    }

    /**
     * 构建待写行
     * 1. 将需要写入的字段内容用逗号分隔。
     * @param t 元素
     * @param fieldBeans 字段列表
     * @return 结果
     */
    private Optional<String> buildWriteLine(final T t,
                                            final List<FieldBean> fieldBeans) {
        if(ObjectUtil.isNull(t)) {
            return Optional.empty();
        }
        List<String> stringList = Guavas.newArrayList(fieldBeans.size());

        IWriteConverter converter = new StringWriteConverter();
        try {
            for(FieldBean bean : fieldBeans) {
                final Optional<CSV> csvOptional = bean.annotationOptByType(CSV.class);
                if(csvOptional.isPresent()) {
                    converter = csvOptional.get().writeConverter().newInstance();
                }
                final Object object = bean.field().get(t);
                String string = converter.convert(object);
                stringList.add(string);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommonRuntimeException(e);
        }

        String writeLine = CollectionUtil.join(stringList, PunctuationConst.COMMA);
        return Optional.ofNullable(writeLine);

    }

    /**
     * 构建表头
     * （1）指定注解，且 label 不为空，则使用 label
     * （2）使用 field.name()
     *
     * 所有字段 label 通过逗号连接。
     * @param fieldBeanList 元素列表
     * @return 对应的 head 字符串
     */
    private String buildWriteHead(List<FieldBean> fieldBeanList) {
        List<String> headList = Guavas.newArrayList(fieldBeanList.size());

        for(FieldBean bean : fieldBeanList) {
            String name = bean.name();
            Optional<CSV> csvOptional = bean.annotationOptByType(CSV.class);
            if(csvOptional.isPresent()) {
                String label = csvOptional.get().label();
                if(StringUtil.isNotEmpty(label)) {
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
        Map<Integer, FieldBean> readMapping = getReadMapping(context.readClass(), context.sort());
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
        final Class<T> readClass = context.readClass();
        List<T> list = Guavas.newArrayList(readableLines.size());

        try {
            for (String line : readableLines) {
                // 跳过空白行
                if (StringUtil.isEmpty(line)) {
                    continue;
                }

                // 设置对象内容
                T instance = readClass.newInstance();
                List<String> stringList = splitCsvLine(line);

                // 长度判断(选择二者的最小值)
                final int minSize = getMinSize(stringList.size(), readMapping.size());
                for (int i = 0; i < minSize; i++) {
                    final String value = stringList.get(i);
                    final Field field = readMapping.get(i).field();

                    final Object object = convertReadValue(value, field);
                    field.set(object, instance);
                }

                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommonRuntimeException(e);
        }
        return list;
    }

    /**
     * 获取排序后的字段
     * @param tClass 类
     * @param sort 排序
     * @param type 操作方式
     * @return 列表
     */
    public List<FieldBean> getSortedFields(final Class tClass,
                                           final ISort sort,
                                           final CsvOperType type) {
        List<Field> allFields = ClassUtil.getAllFieldList(tClass);
        if(CollectionUtil.isEmpty(allFields)) {
            return Collections.emptyList();
        }

        //sort
        List<Field> sortedFields = sort.sort(allFields);

        // map 处理
        //1. 没有注解，正常处理。
        //2. 有注解，但是 require=false 则跳过。
        List<FieldBean> resultList = Guavas.newArrayList(sortedFields.size());
        for (Field field : sortedFields) {
            CSV csv = null;
            if (field.isAnnotationPresent(CSV.class)) {
                csv = field.getAnnotation(CSV.class);
                if (CsvOperType.READ.equals(type)
                    && !csv.readRequire()) {
                    continue;
                }
                if(CsvOperType.WRITE.equals(type)
                    && !csv.writeRequire()) {
                    continue;
                }
            }

            FieldBean fieldBean = new FieldBean();
            fieldBean.field(field)
                    .name(field.getName())
                    .annotation(csv);
            //添加到列表中
            resultList.add(fieldBean);
        }

        return resultList;
    }

    /**
     * 获取读取的映射关系
     *
     * @param tClass 类
     * @param sort   排序信息
     * @return 映射关系
     */
    private Map<Integer, FieldBean> getReadMapping(final Class<T> tClass,
                                               final ISort sort) {
        List<FieldBean> sortedFields = this.getSortedFields(tClass, sort,
                CsvOperType.READ);

        return MapUtil.toIndexMap(sortedFields);
    }

    /**
     * 分隔 csv
     *
     * @param line 原始行
     * @return 字符串列表
     */
    private List<String> splitCsvLine(final String line) {
        String fitLine = line;

        // 如果是逗号结尾，需要添加空格。避免 split 缺失问题。
        if (fitLine.endsWith(PunctuationConst.COMMA)) {
            fitLine = fitLine + StringUtil.BLANK;
        }

        String[] strings = fitLine.split(PunctuationConst.COMMA);
        return CollectionUtil.arrayToList(strings);
    }

    /**
     * 对读取的内容信息进行转换
     *
     * @param csvContent csv 文件信息
     * @param field      字段信息
     * @return 转换后的对象
     */
    private Object convertReadValue(final String csvContent, final Field field) {
        try {
            if (field.isAnnotationPresent(CSV.class)) {
                CSV csv = field.getAnnotation(CSV.class);
                Class<? extends IReadConverter> readConverterClass = csv.readConverter();
                return readConverterClass.newInstance().convert(csvContent);
            }

            // 直接返回消息本身
            return csvContent;
        } catch (InstantiationException | IllegalAccessException e) {
           throw new CommonRuntimeException(e);
        }
    }

    /**
     * 获取最小的大小
     *
     * @param sizeOne 大小1
     * @param sizeTwo 大小2
     * @return 结果
     */
    private int getMinSize(final int sizeOne, final int sizeTwo) {
        return sizeOne > sizeTwo ? sizeTwo : sizeOne;
    }

}
