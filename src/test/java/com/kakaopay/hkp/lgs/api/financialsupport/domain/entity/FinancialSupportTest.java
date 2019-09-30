package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.helper.LocalGovernmentCodeGenerator;
import com.kakaopay.hkp.lgs.exception.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
public class FinancialSupportTest {

    @Test
    public void builderTest() {

        long id = 9010L;

        LocalGovernment localGovernment = new LocalGovernment(id, "광양시");

        String target = "광명시 소재 중소기업으로서 광명시장이 추천한 자";
        String usage = "운전";
        String limit = "3억원 이내";
        String rate = "2.00%";
        String institute = "광명시";
        String mgmt = "광명지점";
        String reception = "전 영업점";

        FinancialSupport financialSupport = FinancialSupport.builder()
                .id(id)
                .interestDifferenceSupportRatio(rate)
                .localGovernment(localGovernment)
                .managementBranch(mgmt)
                .receptionBranch(reception)
                .referrelInstitute(institute)
                .supportLimitAmount(limit)
                .supportTarget(target)
                .usage(usage)
                .build();

        assertThat(financialSupport.getId()).isEqualTo(id);
        assertThat(financialSupport.getLocalGovernment()).isEqualTo(localGovernment);
        assertThat(financialSupport.getSupportTarget()).isEqualTo(target);
        assertThat(financialSupport.getUsage()).isEqualTo(usage);
        assertThat(financialSupport.getSupportLimitAmount()).isEqualTo(limit);
        assertThat(financialSupport.getInterestDifferenceSupportRatio()).isEqualTo(rate);
        assertThat(financialSupport.getReferrelInstitute()).isEqualTo(institute);
        assertThat(financialSupport.getManagementBranch()).isEqualTo(mgmt);
        assertThat(financialSupport.getReceptionBranch()).isEqualTo(reception);
    }

    @Test
    public void equalsTest() {
        assertThat(FinancialSupport.builder().id(1L).build()).isEqualTo(FinancialSupport.builder().id(1L).build());
        assertThat(FinancialSupport.builder().id(1L).build()).isNotEqualTo(FinancialSupport.builder().id(2L).build());
    }
}