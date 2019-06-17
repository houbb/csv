package com.github.houbb.csv.model;

import java.util.*;

/**
 * 支持集合
 * （1）array
 * （2）list/set
 * （3）map
 * @author binbin.hou
 * @since 0.0.3
 */
public class UserCollection {

    private String[] arrays;

    private LinkedList<String> lists;

    private Map<String, String> maps;

    private Set<String> sets;

    public String[] getArrays() {
        return arrays;
    }

    public void setArrays(String[] arrays) {
        this.arrays = arrays;
    }

    public LinkedList<String> getLists() {
        return lists;
    }

    public void setLists(LinkedList<String> lists) {
        this.lists = lists;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public Set<String> getSets() {
        return sets;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "arrays=" + Arrays.toString(arrays) +
                ", lists=" + lists +
                ", maps=" + maps +
                ", sets=" + sets +
                '}';
    }

}
