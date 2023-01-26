package com.teak.core.function;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/16
 */
public interface Change<U, T, R> {
    R changeType(U u, T t, R r);
}
