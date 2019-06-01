package com.github.houbb.csv.exception;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.response.respcode.RespCode;

/**
 * @author binbin.hou
 * @since 1.0.0
 * 添加：
 *
 * commonRespCode
 * 反射失败。
 *
 */
@CommonEager
public enum  CsvRespCode implements RespCode {

    /**
     * 系统异常
     */
    SYSTEM_ERROR("00001", "系统内部异常"),
    REFLECTION_NEW_INSTANCE_FAIL("00002", "通过反射创建实例失败"),

    /**
     * 业务处理异常
     */
    FIELD_CSV_LENGTH_NOT_MATCH("10001", "字段和csv文件数量不对应");

    private final String code;
    private final String msg;

    CsvRespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
