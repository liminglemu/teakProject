package com.teak.core.pattern;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class DoubleSearchLazySingleton {

    private static volatile DoubleSearchLazySingleton doubleSearchLazySingleton;

    private DoubleSearchLazySingleton() {
    }

    private static DoubleSearchLazySingleton doubleSearchLazySingles() {
        if (doubleSearchLazySingleton == null) {
            synchronized (DoubleSearchLazySingleton.class) {
                if (doubleSearchLazySingleton == null) {
                    doubleSearchLazySingleton = new DoubleSearchLazySingleton();
                }
            }
        }
        return doubleSearchLazySingleton;
    }
}
