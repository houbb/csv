package com.github.houbb.csv.model;

import java.io.Serializable;

/**
 * <p> project: csv-UserWithSerialId </p>
 * <p> create on 2020/2/10 9:07 </p>
 *
 * @author Administrator
 * @since 0.0.1
 */
public class UserWithSerialId implements Serializable {

    private static final long serialVersionUID = -6243135423619414366L;

    private String name;

    private int age;

    public String name() {
        return name;
    }

    public UserWithSerialId name(String name) {
        this.name = name;
        return this;
    }

    public int age() {
        return age;
    }

    public UserWithSerialId age(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "UserWithSerialId{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
