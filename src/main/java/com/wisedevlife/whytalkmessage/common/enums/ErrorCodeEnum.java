package com.wisedevlife.whytalkmessage.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    SYSTEM_ERROR(-1, "system error");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
