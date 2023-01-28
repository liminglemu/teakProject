package com.teak.core.pattern;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class HungryHanStyle {

    private static final HungryHanStyle hungryHanStyle = new HungryHanStyle();

    private HungryHanStyle() {
    }

    public static HungryHanStyle getInstance() {
        System.out.println(" 这是饿汉式单例实例创建 ");
        return hungryHanStyle;
    }
}
