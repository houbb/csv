package com.github.houbb.csv.support.convert.read.entry;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.support.convert.read.CommonReadConverter;
import com.github.houbb.csv.util.CsvFieldUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.support.instance.impl.InstanceFactory;
import com.github.houbb.heaven.support.sort.ISort;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 针对单个对象的信息读取
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class EntryReadConverter<T> implements IReadConverter<T> {

    @Override
    public T convert(SingleReadContext<T> context) {
        //1. 基础信息
        final Class<T> clazz = context.classType();
        final String csvLine = context.value();
        final ISort sort = context.sort();

        Map<Integer, FieldBean> readMapping = CsvFieldUtil.getReadMapping(clazz, sort);

        // 设置对象内容
        T instance = ClassUtil.newInstance(clazz);

        List<String> stringList = splitCsvLine(csvLine);

        // 长度判断(选择二者的最小值)
        final int minSize = getMinSize(stringList.size(), readMapping.size());
        try {
            for (int i = 0; i < minSize; i++) {
                final String value = stringList.get(i);
                final Field field = readMapping.get(i).field();

                final Object object = convertReadValue(value, field);
                field.set(instance, object);
            }

            return instance;
        } catch (IllegalAccessException e) {
            throw new CommonRuntimeException(e);
        }
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
            SingleReadContext context = new SingleReadContext();
            context.value(csvContent).field(field);

            // 指定转换器的处理
            if (field.isAnnotationPresent(Csv.class)) {
                Csv csv = field.getAnnotation(Csv.class);
                Class<? extends IReadConverter> readConverterClass = csv.readConverter();
                return readConverterClass.newInstance().convert(context);
            }

            // 通用转换器的处理
            return InstanceFactory.getInstance()
                    .singleton(CommonReadConverter.class)
                    .convert(context);

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
