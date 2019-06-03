package com.github.houbb.csv.model;

import com.github.houbb.csv.annotation.Csv;
import com.github.houbb.csv.convert.ReadDateConvert;
import com.github.houbb.csv.convert.WriteDateConvert;

import java.util.Date;

/**
 * 基于注解的测试案例
 * @author binbin.hou
 * @since 0.0.2
 */
public class UserAnnotation {

    @Csv(label = "名称")
    private String name;

    @Csv(label = "密码", readRequire = false, writeRequire = false)
    private String password;

    @Csv(label = "生日", readConverter = ReadDateConvert.class, writeConverter = WriteDateConvert.class)
    private Date birthday;

    public String name() {
        return name;
    }

    public UserAnnotation name(String name) {
        this.name = name;
        return this;
    }

    public String password() {
        return password;
    }

    public UserAnnotation password(String password) {
        this.password = password;
        return this;
    }

    public Date birthday() {
        return birthday;
    }

    public UserAnnotation birthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "UserAnnotation{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}
