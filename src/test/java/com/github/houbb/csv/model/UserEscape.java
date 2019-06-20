package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.CsvEntry;

import java.util.List;
import java.util.Map;

/**
 * 特殊字符转义处理
 * @author binbin.hou
 * @since 0.0.6
 */
public class UserEscape {

    /**
     * 使用 ,
     */
    private String name;

    /**
     * 使用 map =
     */
    private Map<String, String> map;

    /**
     * 使用 |
     */
    private List<String> nameList;

    /**
     * 使用 :
     */
    @CsvEntry
    private User user;

    public String name() {
        return name;
    }

    public UserEscape name(String name) {
        this.name = name;
        return this;
    }

    public List<String> nameList() {
        return nameList;
    }

    public UserEscape nameList(List<String> nameList) {
        this.nameList = nameList;
        return this;
    }

    public User user() {
        return user;
    }

    public UserEscape user(User user) {
        this.user = user;
        return this;
    }

    public Map<String, String> map() {
        return map;
    }

    public UserEscape map(Map<String, String> map) {
        this.map = map;
        return this;
    }

    @Override
    public String toString() {
        return "UserEscape{" +
                "name='" + name + '\'' +
                ", nameList=" + nameList +
                ", user=" + user +
                ", map=" + map +
                '}';
    }

}
