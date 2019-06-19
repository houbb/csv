package com.github.houbb.csv.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class SplitTest {

    @Test
    public void splitTest() {
        final String original = "1:2:3:4";
        System.out.println(Arrays.toString(original.split(":")));
    }

    @Test
    public void split2Test() {
        final String original = "2:3:31::32:4:5";
        System.out.println(Arrays.toString(original.split(":")));
    }

}
