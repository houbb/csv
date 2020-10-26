package com.github.houbb.csv.enums;

import com.github.houbb.csv.api.ICodeDesc;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public enum StatusEnum implements ICodeDesc {

    SUCCESS("ok", "成功"),
    FAIL("fail", "失败"),
    ;

    private final String code;
    private final String desc;

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public ICodeDesc[] list() {
        return StatusEnum.values();
    }

}
