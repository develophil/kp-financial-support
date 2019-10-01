package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.InterestDifferenceSupportRatio;
import com.kakaopay.hkp.lgs.exception.ApiException;
import com.kakaopay.hkp.lgs.exception.ApiExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RateCompositor {

    public static final String MAX_LIMIT_RATE = "100%";

    @Getter
    @AllArgsConstructor
    public enum RateUnit {
        RANGE("~"),
        ALL("대출이자 전액"),
        PERCENT("%")
        ;

        private String delimeter;


    }
    public static String getInterestDifferenceSupportRatio(InterestDifferenceSupportRatio interestDifferenceSupportRatio) {

        String interestDifferenceSupportFromRatio = interestDifferenceSupportRatio.getInterestDifferenceSupportFromRatio();
        String interestDifferenceSupportToRatio = interestDifferenceSupportRatio.getInterestDifferenceSupportToRatio();

        if (interestDifferenceSupportToRatio.equals(MAX_LIMIT_RATE)) {
            return RateUnit.ALL.getDelimeter();
        } else if (interestDifferenceSupportFromRatio == null) {
            return interestDifferenceSupportToRatio;
        } else {
            return interestDifferenceSupportFromRatio + RateUnit.RANGE.getDelimeter() + interestDifferenceSupportToRatio;
        }
    }
    private static String[] split(String rateStr) {
        return rateStr.split(RateUnit.RANGE.getDelimeter());
    }

    public static void setSplitRate(InterestDifferenceSupportRatio interestDifferenceSupportRatio, String rateStr) {

        String[] split = split(rateStr);

        if (split.length == 1) {

            if (split[0].equals(RateUnit.ALL.getDelimeter())) {
                interestDifferenceSupportRatio.setInterestDifferenceSupportFromRatio(null);
                interestDifferenceSupportRatio.setInterestDifferenceSupportToRatio(MAX_LIMIT_RATE);
            } else {
                interestDifferenceSupportRatio.setInterestDifferenceSupportFromRatio(null);
                interestDifferenceSupportRatio.setInterestDifferenceSupportToRatio(split[0]);
            }

        } else if (split.length == 2) {
            interestDifferenceSupportRatio.setInterestDifferenceSupportFromRatio(split[0]);
            interestDifferenceSupportRatio.setInterestDifferenceSupportToRatio(split[1]);
        } else {
            throw new ApiException(ApiExceptionCode.INVALID_VALUE);
        }
    }
}
