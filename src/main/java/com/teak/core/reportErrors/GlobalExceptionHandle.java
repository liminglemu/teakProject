package com.teak.core.reportErrors;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/23
 */
public class GlobalExceptionHandle extends RuntimeException {
    private String message;

    public GlobalExceptionHandle(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GlobalExceptionHandle setMessage(String message) {
        this.message = message;
        return this;
    }
}
