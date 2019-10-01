package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.SupportLimit;
import com.kakaopay.hkp.lgs.exception.ApiException;
import com.kakaopay.hkp.lgs.exception.ApiExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LimitCompositor {

    public static final String UNDER = " 이내";

    public static String toLimitAmountString(SupportLimit supportLimit) {

        if (supportLimit.isFixedAmount()) {
            return supportLimit.getSupportLimitExponent() + LimitWonUnit.value(supportLimit.getSupportLimitDigits()).getAmountKorean() + UNDER;
        } else {
            return LimitWonUnit.LIMIT.getAmountKorean() + UNDER;
        }
    }

    public static void setSupportLimit(SupportLimit supportLimit, String limit) {

        final String millionAmountKorean = LimitWonUnit.MILLION.getAmountKorean();
        final String billionDivTenAmountKorean = LimitWonUnit.BILLION_DIV_TEN.getAmountKorean();

        if (limit.contains(LimitWonUnit.LIMIT.getAmountKorean())) {
            supportLimit.setSupportLimitExponent(0);
            supportLimit.setSupportLimitDigits(0);
        } else if (limit.contains(millionAmountKorean)) {
            supportLimit.setSupportLimitExponent(Integer.parseInt(separateNumber(limit, millionAmountKorean)));
            supportLimit.setSupportLimitDigits(LimitWonUnit.MILLION.getDigits());
        } else if (limit.contains(billionDivTenAmountKorean)) {
            supportLimit.setSupportLimitExponent(Integer.parseInt(separateNumber(limit, billionDivTenAmountKorean)));
            supportLimit.setSupportLimitDigits(LimitWonUnit.BILLION_DIV_TEN.getDigits());
        } else {
            throw new ApiException(ApiExceptionCode.INVALID_VALUE);
        }
    }

    private static String separateNumber(String limit, String millionAmountKorean) {
        return limit.substring(0, limit.indexOf(millionAmountKorean));
    }

    @Getter
    @AllArgsConstructor
    private enum LimitWonUnit {
        LIMIT("추천금액", 0),
        MILLION("백만원", 6),
        BILLION_DIV_TEN("억원", 8)
        ;

        private String amountKorean;
        private int digits;

        public static LimitWonUnit value(int digits) {
            for (LimitWonUnit limitWonUnit : LimitWonUnit.values()) {
                if (limitWonUnit.getDigits() == digits) {
                    return limitWonUnit;
                }
            }
            return null;
        }
    }


}
