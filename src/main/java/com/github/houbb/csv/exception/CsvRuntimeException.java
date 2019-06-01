package com.github.houbb.csv.exception;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.response.respcode.RespCode;
import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@CommonEager
public class CsvRuntimeException extends RuntimeException implements RespCode {

    /**
     * 错误编码
     */
    private final RespCode respCode;

    public CsvRuntimeException(RespCode respCode) {
        this.respCode = respCode;
    }

    public CsvRuntimeException(String message, RespCode respCode) {
        super(message);
        this.respCode = respCode;
    }

    public CsvRuntimeException(String message, Throwable cause, RespCode respCode) {
        super(message, cause);
        this.respCode = respCode;
    }

    public CsvRuntimeException(Throwable cause, RespCode respCode) {
        super(cause);
        this.respCode = respCode;
    }

    public CsvRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RespCode respCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.respCode = respCode;
    }

    @Override
    public String getCode() {
        return respCode.getCode();
    }

    /**
     * 返回枚举本身的消息提示
     * @return 枚举消息提示
     */
    @Override
    public String getMsg() {
        return respCode.getMsg();
    }

    /**
     * 将枚举的信息+错误的信息
     * @return 混合的信息
     */
    public String getMsgMixed() {
        if(StringUtil.isNotEmpty(super.getMessage())) {
            return respCode.getMsg()+","+super.getMessage();
        }
        return this.getMsg();
    }

    /**
     * 如果指定了 Message,则直接返回 message
     * 否则返回枚举本身的信息
     * @return 信息
     */
    public String getMsgPerfer() {
        if(StringUtil.isNotEmpty(super.getMessage())) {
            return super.getMessage();
        }
        return this.getMsg();
    }

}
