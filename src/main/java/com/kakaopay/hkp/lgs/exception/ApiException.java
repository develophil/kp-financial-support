package com.kakaopay.hkp.lgs.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 8451482185184253617L;

    private String exceptionCode;

    public ApiException(ExceptionCode exceptionCode, String... args) {
        super(exceptionCode.getMessage(args));
        this.exceptionCode = exceptionCode.getCode();
    }
}
