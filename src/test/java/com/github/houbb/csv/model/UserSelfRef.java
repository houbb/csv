package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.CsvEntry;

/**
 * 用户自引用
 * 测试目的：自己引用自己，是否会出问题。
 * @author binbin.hou
 * @since 0.0.7
 */
public class UserSelfRef {

    private String name;

    @CsvEntry
    private UserSelfRef selfRef;

    public String name() {
        return name;
    }

    public UserSelfRef name(String name) {
        this.name = name;
        return this;
    }

    public UserSelfRef selfRef() {
        return selfRef;
    }

    public UserSelfRef selfRef(UserSelfRef selfRef) {
        this.selfRef = selfRef;
        return this;
    }

    @Override
    public String toString() {
        return "UserSelfRef{" +
                "name='" + name + '\'' +
                ", selfRef=" + selfRef +
                '}';
    }

}
