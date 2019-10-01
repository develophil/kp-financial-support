package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.InterestDifferenceSupportRatio;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RateCompositorTest {

    @Test
    public void getInterestDifferenceSupportRatio() {

        String singleInt = "3%";
        String singleFloat = "3.00%";
        String doubleFloat = "0.1%~3.5%";
        String doubleIntFloat = "2%~2.5%";
        String all = "대출이자 전액";

        assertThat(RateCompositor.getInterestDifferenceSupportRatio(new InterestDifferenceSupportRatio(all))).isEqualTo(all);
        assertThat(RateCompositor.getInterestDifferenceSupportRatio(new InterestDifferenceSupportRatio(singleInt))).isEqualTo(singleInt);
        assertThat(RateCompositor.getInterestDifferenceSupportRatio(new InterestDifferenceSupportRatio(singleFloat))).isEqualTo(singleFloat);
        assertThat(RateCompositor.getInterestDifferenceSupportRatio(new InterestDifferenceSupportRatio(doubleFloat))).isEqualTo(doubleFloat);
        assertThat(RateCompositor.getInterestDifferenceSupportRatio(new InterestDifferenceSupportRatio(doubleIntFloat))).isEqualTo(doubleIntFloat);
    }
}