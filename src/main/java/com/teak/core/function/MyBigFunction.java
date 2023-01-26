package com.teak.core.function;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/16
 */
@FunctionalInterface
public interface MyBigFunction<T, U, R> {
    R bigAction(T t, U u);
}
