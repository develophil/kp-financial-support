package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.DefaultFinancialSupportTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class FinancialSupportTest extends DefaultFinancialSupportTest {

    @Test
    public void builderTest() {

        FinancialSupport financialSupport = getDefaultTestFinancialSupport();

        assertThat(financialSupport.getId()).isEqualTo(testId);
        assertThat(financialSupport.getLocalGovernment()).isEqualTo(getDefaultTestLocalGovernment());
        assertThat(financialSupport.getSupportTarget()).isEqualTo(testTarget);
        assertThat(financialSupport.getUsage()).isEqualTo(testUsage);
        assertThat(financialSupport.getSupportLimit()).isEqualTo(testLimit);
        assertThat(financialSupport.getInterestDifferenceSupportRatio()).isEqualTo(testRate);
        assertThat(financialSupport.getReferrelInstitute()).isEqualTo(testInstitute);
        assertThat(financialSupport.getManagementBranch()).isEqualTo(testMgmt);
        assertThat(financialSupport.getReceptionBranch()).isEqualTo(testReception);
    }

    @Test
    public void equalsTest() {
        assertThat(FinancialSupport.builder().id(1L).build()).isEqualTo(FinancialSupport.builder().id(1L).build());
        assertThat(FinancialSupport.builder().id(1L).build()).isNotEqualTo(FinancialSupport.builder().id(2L).build());
    }
}