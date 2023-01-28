package com.teak.core.pattern;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class EnumerateLazySingleton {

    public static EnumerateLazySingleton getInstance() {
        return EnumSingleton.LazySingleton.enumerateLazySingleton;
    }

    private enum EnumSingleton {
        LazySingleton();
        private final EnumerateLazySingleton enumerateLazySingleton;

        EnumSingleton() {
            System.out.println("枚举类懒汉式单例加载");
            this.enumerateLazySingleton = new EnumerateLazySingleton();
        }
    }
}
