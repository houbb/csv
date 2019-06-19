package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.CsvEntry;

/**
 * 用户明细测试
 * @author binbin.hou
 * @since 0.0.5
 */
public class UserEntry {

    /**
     * 名称
     */
    private String name;

    /**
     * 内嵌的用户信息
     */
    @CsvEntry
    private User user;

    public String name() {
        return name;
    }

    public UserEntry name(String name) {
        this.name = name;
        return this;
    }

    public User user() {
        return user;
    }

    public UserEntry user(User user) {
        this.user = user;
        return this;
    }

}
