package com.github.houbb.csv.support.convert.mapping;

import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.context.SingleReadContext;
import com.github.houbb.csv.util.CsvInnerUtil;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.Map;

/**
 * 读取转换器，将 string 转换为 field
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class ReadMappingConverter implements IReadConverter<String> {

    @Override
    public String convert(SingleReadContext context) {
        String value = context.value();
        if(StringUtil.isEmpty(value)) {
            return value;
        }

        Map<String, String> map = CsvInnerUtil.getMappingMap(context.csv().readMapping());
        for(Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getValue().equalsIgnoreCase(value)) {
                return entry.getKey();
            }
        }
        return value;
    }

}
