package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.SupportLimit;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class LimitCompositorTest {

    @Test
    public void toLimitAmountString() {

        String all = "추천금액 이내";
        String limit_8000 = "8억원 이내";
        String limit_30 = "30백만원 이내";

        Assertions.assertThat(LimitCompositor.toLimitAmountString(new SupportLimit(all))).isEqualTo(all);
        Assertions.assertThat(LimitCompositor.toLimitAmountString(new SupportLimit(limit_8000))).isEqualTo(limit_8000);
        Assertions.assertThat(LimitCompositor.toLimitAmountString(new SupportLimit(limit_30))).isEqualTo(limit_30);
    }
}