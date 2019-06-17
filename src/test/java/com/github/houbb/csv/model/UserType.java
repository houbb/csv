package com.github.houbb.csv.model;

import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class UserType {

    private Map<String, String> stringMap;

    private Map<?, ?> wildcardMap;

    private Map<Object, Object> objectMap;

    /**
     * String 的父类
     */
    private List<? super String> wildcardSuperList;

    /**
     * String 的子类
     */
    private List<? extends String> wildcardExtendsList;

    private List<?> wildcardList;

    private String[] strings;

    private Object[] objects;

    private List<String> stringList;

    private List<Object> objectList;

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public List<?> getWildcardList() {
        return wildcardList;
    }

    public void setWildcardList(List<?> wildcardList) {
        this.wildcardList = wildcardList;
    }

    public List<? super String> getWildcardSuperList() {
        return wildcardSuperList;
    }

    public void setWildcardSuperList(List<? super String> wildcardSuperList) {
        this.wildcardSuperList = wildcardSuperList;
    }

    public List<? extends String> getWildcardExtendsList() {
        return wildcardExtendsList;
    }

    public void setWildcardExtendsList(List<? extends String> wildcardExtendsList) {
        this.wildcardExtendsList = wildcardExtendsList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<?, ?> getWildcardMap() {
        return wildcardMap;
    }

    public void setWildcardMap(Map<?, ?> wildcardMap) {
        this.wildcardMap = wildcardMap;
    }

    public Map<Object, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<Object, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public static void main(String[] args) {
        List<Field> fieldList = ClassUtil.getAllFieldList(UserType.class);
        for(Field field : fieldList) {
            System.out.println(ReflectFieldUtil.getComponentType(field));
        }
    }

}
