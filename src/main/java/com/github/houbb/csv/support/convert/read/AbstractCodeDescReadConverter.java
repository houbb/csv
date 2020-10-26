package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.ICodeDesc;
import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.context.SingleReadContext;

/**
 * 获取 label 描述映射为 code 转换类
 * @author binbin.hou
 * @see ICodeDesc 接口
 * @since 0.1.0
 */
public abstract class AbstractCodeDescReadConverter<E extends ICodeDesc> implements IReadConverter<String> {

    /**
     * 获取映射类
     * @return 映射类
     * @since 0.1.0
     */
    protected abstract E[] codeDescArray();

    @Override
    public String convert(SingleReadContext context) {
        String label = context.value();

        ICodeDesc[] codeDescArray = codeDescArray();
        for(ICodeDesc entry : codeDescArray) {
            if(entry.getDesc().equalsIgnoreCase(label)) {
                return entry.getCode();
            }
        }

        return label;
    }

}
