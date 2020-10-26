package com.github.houbb.csv.api;

/**
 * 枚举值编码和描述接口
 * @author binbin.hou
 * @since 0.1.0
 */
public interface ICodeDesc {

    /**
     * 获取编码
     * @return 编码
     * @since 0.1.0
     */
    String getCode();

    /**
     * 获取描述
     * @return 描述
     * @since 0.1.0
     */
    String getDesc();

    /**
     * 列表
     * @return 列表
     * @since 0.1.0
     */
    ICodeDesc[] list();

}
