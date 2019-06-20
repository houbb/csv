package com.github.houbb.csv.support.convert.write.entry;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.constant.CsvConst;
import com.github.houbb.csv.constant.CsvOperateType;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.support.convert.write.CommonWriteConverter;
import com.github.houbb.csv.util.CsvFieldUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.response.exception.CommonRuntimeException;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.Optional;

import java.util.List;

/**
 * 针对单个对象的信息构建
 * （1）必须为对象
 * （2）必须为用户自定义对象
 * <p>
 * 1. array
 * 2. 集合
 * 3. map
 * 4. 对象
 * 5. 其他对象
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class EntryWriteConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        final Object value = context.element();
        if (ObjectUtil.isNull(value)) {
            return StringUtil.EMPTY;
        }

        // 获取字段信息
        Class clazz = value.getClass();
        List<FieldBean> fieldBeanList = CsvFieldUtil.getSortedFields(clazz, context.sort(), CsvOperateType.WRITE);
        if (CollectionUtil.isEmpty(fieldBeanList)) {
            return StringUtil.EMPTY;
        }

        return buildWriteLine(context, value, fieldBeanList);
    }

    /**
     * 构建待写行
     * 1. 将需要写入的字段内容用逗号分隔。
     *
     * @param context 上下文
     * @param t 元素
     * @param fieldBeans 字段列表
     * @return 结果
     */
    private String buildWriteLine(final SingleWriteContext context,
                                  final Object t, final List<FieldBean> fieldBeans) {
        if (ObjectUtil.isNull(t)) {
            return StringUtil.EMPTY;
        }

        List<String> stringList = Guavas.newArrayList(fieldBeans.size());
        // 默认使用通用转换
        IWriteConverter converter = Instances.singletion(CommonWriteConverter.class);
        try {
            for (FieldBean bean : fieldBeans) {
                final Optional<Csv> csvOptional = bean.annotationOptByType(Csv.class);
                if (csvOptional.isPresent()) {
                    converter = csvOptional.get().writeConverter().newInstance();
                }
                final Object object = bean.field().get(t);
                // 上下文的处理
                SingleWriteContext entryContext = new SingleWriteContext();
                entryContext.value(object).field(bean.field()).element(t)
                    .sort(context.sort())
                    .split(CsvConst.COMMA);
                String string = converter.convert(entryContext);
                stringList.add(string);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommonRuntimeException(e);
        }

        return CollectionUtil.join(stringList, context.split());
    }

}
