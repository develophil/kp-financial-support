package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.exception.ApiException;
import com.kakaopay.hkp.lgs.exception.ApiExceptionCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalGovernmentCodeGenerator implements CodeGenerator {

    public static final String REGISTER_CODE_PREFIX = "REG";

    public static String generateCode(long id) {
        if (id < 0 || id >= 10000) {
            throw new ApiException(ApiExceptionCode.INVALID_PARAMETER, "id : " + id);
        }

        return String.format(REGISTER_CODE_PREFIX + "%04d", id);
    }
}
