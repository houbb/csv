package com.github.houbb.csv.util;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.annotation.CsvEntry;
import com.github.houbb.csv.constant.CsvOperateType;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.reflect.model.FieldBean;
import com.github.houbb.heaven.support.sort.ISort;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CSV 字段工具类
 * @author binbin.hou
 * @since 0.0.4
 */
public final class CsvFieldUtil {

    private CsvFieldUtil(){}

    /**
     * 本地缓存
     * key: class.fullName + type
     */
    private static final Map<String, List<FieldBean>> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取排序后的字段
     *
     * @param tClass 类
     * @param sort   排序
     * @param type   操作方式
     * @return 列表
     */
    public static List<FieldBean> getSortedFields(final Class tClass,
                                            final ISort sort,
                                            final CsvOperateType type) {
        final String cacheKey = buildCacheKey(tClass, type);
        if(CACHE.containsKey(cacheKey)) {
            return CACHE.get(cacheKey);
        }

        List<FieldBean> resultList = Guavas.newArrayList();
        List<Field> allFields = ClassUtil.getAllFieldList(tClass);
        if (CollectionUtil.isEmpty(allFields)) {
            resultList = Collections.emptyList();
        }

        //sort
        List<Field> sortedFields = sort.sort(allFields);

        // map 处理
        //1. 没有注解，正常处理。
        //2. 有注解，但是 require=false 则跳过。
        for (Field field : sortedFields) {
            Csv csv = null;
            if (field.isAnnotationPresent(Csv.class)) {
                csv = field.getAnnotation(Csv.class);
                if (CsvOperateType.READ.equals(type)
                        && !csv.readRequire()) {
                    continue;
                }
                if (CsvOperateType.WRITE.equals(type)
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

        // 存放在 cache 中
        CACHE.put(cacheKey, resultList);
        return resultList;
    }

    /**
     * 构建缓存 cache
     * （1）读写是要区分开的
     * String.class, CsvOperateType.READ
     * 输出：java.lang.String_READ
     * @param tClass 类
     * @param type 操作类型
     * @return 缓存 key
     */
    private static String buildCacheKey(final Class tClass,
                                 final CsvOperateType type) {
        return tClass.getName()+ PunctuationConst.UNDERLINE+type.name();
    }

    /**
     * 获取读取的映射关系
     *
     * @param tClass 类
     * @param sort   排序信息
     * @return 映射关系
     */
    public static Map<Integer, FieldBean> getReadMapping(final Class tClass,
                                                   final ISort sort) {
        List<FieldBean> sortedFields = CsvFieldUtil.getSortedFields(tClass, sort,
                CsvOperateType.READ);

        return MapUtil.toIndexMap(sortedFields);
    }

    /**
     * 是否进行明细相关处理
     * @param field 字段信息
     * @return 是否
     * @since 0.0.5
     */
    public static boolean isEntryAble(final Field field) {
        return ReflectFieldUtil.isAnnotationPresent(field, CsvEntry.class)
                && ClassTypeUtil.isJavaBean(field.getType());
    }

}
