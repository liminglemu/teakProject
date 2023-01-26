package com.teak.core.result;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/17
 */
public enum GeneralResultEnum {
    SUCCESS(200, "成功"),
    FAIL(404, "失败");

    private final int code;
    private final String message;

    GeneralResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
