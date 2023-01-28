package com.teak.core.pattern;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/28
 */
public class DoubleSearchLazySingles {

    private static volatile DoubleSearchLazySingles doubleSearchLazySingles;

    private DoubleSearchLazySingles() {
    }

    private static DoubleSearchLazySingles doubleSearchLazySingles() {
        if (doubleSearchLazySingles == null) {
            synchronized (DoubleSearchLazySingles.class) {
                if (doubleSearchLazySingles == null) {
                    doubleSearchLazySingles = new DoubleSearchLazySingles();
                }
            }
        }
        return doubleSearchLazySingles;
    }
}
