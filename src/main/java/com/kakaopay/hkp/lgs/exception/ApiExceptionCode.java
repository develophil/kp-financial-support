package com.kakaopay.hkp.lgs.exception;

public enum ApiExceptionCode implements ExceptionCode {

    INVALID_PARAMETER("10000", "유효하지 않은 인자값입니다. [%s]"),

    FAIL_READ_FILE("20000", "파일을 읽을 수가 없습니다. [%s]"),
    EMPTY_FILE("20010", "파일이 비어있습니다."),

    NOT_FOUND_RESOURCE("30000", "자원을 찾을 수가 없습니다. [%s]")
    ;

    private String code;
    private String message;

    ApiExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override public String getCode() {
        return this.code;
    }

    @Override public String getMessage(Object... args) {
        return String.format(this.message, args);
    }
}
