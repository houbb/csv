package com.github.houbb.csv.model;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class User {

    private String name;

    private int age;

    private float score;

    private double money;

    private boolean sex;

    private short level;

    private long id;

    private char status;

    private byte coin;

    public String name() {
        return name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public int age() {
        return age;
    }

    public User age(int age) {
        this.age = age;
        return this;
    }

    public float score() {
        return score;
    }

    public User score(float score) {
        this.score = score;
        return this;
    }

    public double money() {
        return money;
    }

    public User money(double money) {
        this.money = money;
        return this;
    }

    public boolean sex() {
        return sex;
    }

    public User sex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public short level() {
        return level;
    }

    public User level(short level) {
        this.level = level;
        return this;
    }

    public long id() {
        return id;
    }

    public User id(long id) {
        this.id = id;
        return this;
    }

    public char status() {
        return status;
    }

    public User status(char status) {
        this.status = status;
        return this;
    }

    public byte coin() {
        return coin;
    }

    public User coin(byte coin) {
        this.coin = coin;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", money=" + money +
                ", sex=" + sex +
                ", level=" + level +
                ", id=" + id +
                ", status=" + status +
                ", coin=" + coin +
                '}';
    }
}
