package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.Csv;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public class UserMapping {

    @Csv(readMapping = "S:成功;F:失败", writeMapping = "S:成功;F:失败")
    private String status;

    public String status() {
        return status;
    }

    public UserMapping status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "UserMapping{" +
                "status='" + status + '\'' +
                '}';
    }
}
