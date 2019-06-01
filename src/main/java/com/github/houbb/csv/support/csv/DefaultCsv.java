package com.github.houbb.csv.support.csv;

import com.github.houbb.csv.annotation.CSV;
import com.github.houbb.csv.api.*;
import com.github.houbb.csv.exception.CsvRespCode;
import com.github.houbb.csv.exception.CsvRuntimeException;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class DefaultCsv<T> implements ICsv<T> {

    @Override
    public void write(IWriteContext<T> context) {

    }

    @Override
    public List<T> read(IReadContext<T> context) {
        try {
            // 1. 构建字段和下标之间的映射关系。
            Map<Integer, Field> readMapping = getReadMapping(context.readClass(), context.sort());
            if(MapUtil.isEmpty(readMapping)) {
                return Collections.emptyList();
            }

            //2. 文件内容
            Path path = Paths.get(context.path());
            List<String> allLines = Files.readAllLines(path, Charset.forName(context.charset()));
            List<String> readableLines = allLines.subList(context.startIndex(), context.endIndex());
            if(CollectionUtil.isEmpty(readableLines)) {
                return Collections.emptyList();
            }

            //3. 映射处理
            final Class<T> readClass = context.readClass();
            List<T> list = Guavas.newArrayList(readableLines.size());
            for(String line : readableLines) {
                // 跳过空白行
                if(StringUtil.isEmpty(line)) {
                    continue;
                }

                // 设置对象内容
                T instance = readClass.newInstance();
                List<String> stringList = splitCsvLine(line);

                // 长度判断(选择二者的最小值)
                final int minSize = getMinSize(stringList.size(), readMapping.size());
                for(int i = 0; i < minSize; i++) {
                    final String value = stringList.get(i);
                    final Field field = readMapping.get(i);

                    final Object object = convertReadValue(value, field);
                    field.set(object, instance);
                }

                list.add(instance);
            }
            return list;
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            throw new CsvRuntimeException(e, CsvRespCode.SYSTEM_ERROR);
        }
    }

    /**
     * 获取读取的映射关系
     * @param tClass 类
     * @param sort 排序信息
     * @return 映射关系
     */
    private Map<Integer, Field> getReadMapping(final Class<T> tClass,
                                               final ISort sort) {
        Map<Integer, Field> map = new HashMap<>();
        List<Field> allFields = ClassUtil.getAllFieldList(tClass);

        if(CollectionUtil.isEmpty(allFields)) {
            return Collections.emptyMap();
        }

        //sort
        List<Field> sortedFields = sort.sort(allFields);

        // map 处理
        //1. 没有注解，正常处理。
        //2. 有注解，但是 readRequire=false 则跳过。
        for(Field field : sortedFields) {
            if(field.isAnnotationPresent(CSV.class)) {
                CSV csv = field.getAnnotation(CSV.class);
                if(!csv.readRequire()) {
                    continue;
                }
            }

            //设置映射关系
            map.put(map.size(), field);
        }
        return map;
    }

    /**
     * 分隔 csv
     * @param line 原始行
     * @return 字符串列表
     */
    private List<String> splitCsvLine(final String line) {
        String fitLine = line;

        // 如果是逗号结尾，需要添加空格。避免 split 缺失问题。
        if(fitLine.endsWith(PunctuationConst.COMMA)) {
            fitLine = fitLine + StringUtil.BLANK;
        }

        String[] strings = fitLine.split(PunctuationConst.COMMA);
        return CollectionUtil.arrayToList(strings);
    }

    /**
     * 对读取的内容信息进行转换
     * @param csvContent csv 文件信息
     * @param field 字段信息
     * @return 转换后的对象
     */
    private Object convertReadValue(final String csvContent, final Field field) {
        try {
            if(field.isAnnotationPresent(CSV.class)) {
                CSV csv = field.getAnnotation(CSV.class);
                Class<? extends IReadConverter> readConverterClass = csv.readConverter();
                return readConverterClass.newInstance().convert(csvContent);
            }

            // 直接返回消息本身
            return csvContent;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CsvRuntimeException(e, CsvRespCode.REFLECTION_NEW_INSTANCE_FAIL);
        }
    }

    /**
     * 获取最小的大小
     * @param sizeOne 大小1
     * @param sizeTwo 大小2
     * @return 结果
     */
    private int getMinSize(final int sizeOne, final int sizeTwo) {
        return sizeOne > sizeTwo ? sizeTwo : sizeOne;
    }

}
