package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.helper.LocalGovernmentCodeGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalGovernmentTest extends DefaultTest {

    private long testId = 5;
    private String testName = "testName";
    private String testCode = LocalGovernmentCodeGenerator.generateCode(testId);

    @Test
    public void builderTest() {

        LocalGovernment localGovernment = new LocalGovernment(testId, testName);

        assertThat(localGovernment.getName()).isEqualTo(testName);
        assertThat(localGovernment.getCode()).isEqualTo(testCode);
    }

    @Test
    public void equalsTest() {

        LocalGovernment localGovernment = new LocalGovernment(testId, testName);
        LocalGovernment localGovernmentSameCode = new LocalGovernment(testId, testName);
        LocalGovernment localGovernmentAnotherCode = new LocalGovernment(10, testName);

        assertThat(localGovernment).isEqualTo(localGovernmentSameCode);
        assertThat(localGovernment).isNotEqualTo(localGovernmentAnotherCode);
    }
}
