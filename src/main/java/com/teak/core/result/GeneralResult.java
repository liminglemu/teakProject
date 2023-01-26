package com.teak.core.result;

import org.jetbrains.annotations.NotNull;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/17
 */
public final class GeneralResult<T> {
    private Integer code;
    private String message;
    private T data;

    private GeneralResult() {
    }

    public static <T> @NotNull GeneralResult<T> ok() {
        GeneralResult<T> generalResult = new GeneralResult<>();
        generalResult.setCode(GeneralResultEnum.SUCCESS.getCode());
        generalResult.setMessage(GeneralResultEnum.SUCCESS.getMessage());
        return generalResult;
    }

    public static <T> @NotNull GeneralResult<T> ok(Integer code, String message, T data) {
        GeneralResult<T> generalResult = new GeneralResult<>();
        generalResult.setCode(code);
        generalResult.setMessage(message);
        generalResult.setData(data);
        return generalResult;
    }

    public static <T> @NotNull GeneralResult<T> ok(@NotNull GeneralResultEnum generalResultEnum, T data) {
        GeneralResult<T> generalResult = new GeneralResult<>();
        generalResult.setCode(generalResultEnum.getCode());
        generalResult.setMessage(generalResultEnum.getMessage());
        if (data != null) {
            generalResult.setData(data);
        }
        return generalResult;
    }

    public GeneralResult<T> message(String message) {
        if (message != null) {
            this.message = message;
        } else {
            this.message = GeneralResultEnum.SUCCESS.getMessage();
        }
        return this;
    }

    public GeneralResult<T> data(T data) {
        this.data = data;
        return this;
    }


    public Integer getCode() {
        return code;
    }

    private GeneralResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    private GeneralResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    private GeneralResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
