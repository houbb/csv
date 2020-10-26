package com.github.houbb.csv.support.convert.read;

import com.github.houbb.csv.api.ICodeDesc;
import com.github.houbb.csv.api.IReadConverter;
import com.github.houbb.csv.support.context.SingleReadContext;

/**
 * 获取编码描述映射转换类
 * @author binbin.hou
 * @see ICodeDesc 接口
 * @since 0.1.0
 */
public abstract class AbstractCodeDescWriteConverter<E extends ICodeDesc> implements IReadConverter<String> {

    /**
     * 获取映射类
     * @return 映射类
     * @since 0.1.0
     */
    protected abstract E[] codeDescArray();

    @Override
    public String convert(SingleReadContext context) {
        String code = context.value();

        ICodeDesc[] codeDescArray = codeDescArray();
        for(ICodeDesc entry : codeDescArray) {
            if(entry.getCode().equalsIgnoreCase(code)) {
                return entry.getDesc();
            }
        }

        return code;
    }

}
