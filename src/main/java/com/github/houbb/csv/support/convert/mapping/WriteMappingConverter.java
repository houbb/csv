package com.github.houbb.csv.support.convert.mapping;

import com.github.houbb.csv.api.IWriteConverter;
import com.github.houbb.csv.support.context.SingleWriteContext;
import com.github.houbb.csv.util.CsvInnerUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.MapUtil;

import java.util.Map;

/**
 * 写入转换器，将 string 转换为 field
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class WriteMappingConverter implements IWriteConverter {

    @Override
    public String convert(SingleWriteContext context) {
        Object value = context.value();
        if(ObjectUtil.isNull(value)) {
            return null;
        }

        String text = (String) context.value();
        if(StringUtil.isEmpty(text)) {
            return text;
        }

        Map<String, String> map = CsvInnerUtil.getMappingMap(context.csv().writeMapping());
        return MapUtil.getMapValue(map, text, text);
    }

}
