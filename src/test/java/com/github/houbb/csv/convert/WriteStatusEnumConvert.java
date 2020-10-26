package com.github.houbb.csv.convert;

import com.github.houbb.csv.enums.StatusEnum;
import com.github.houbb.csv.support.convert.read.AbstractCodeDescWriteConverter;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public class WriteStatusEnumConvert extends AbstractCodeDescWriteConverter<StatusEnum> {

    @Override
    protected StatusEnum[] codeDescArray() {
        return StatusEnum.values();
    }

}
