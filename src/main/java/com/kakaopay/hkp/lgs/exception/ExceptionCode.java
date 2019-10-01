package com.kakaopay.hkp.lgs.exception;

public interface ExceptionCode {
    String getCode();
    String getMessage(Object... args);
}
