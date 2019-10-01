package com.kakaopay.hkp.lgs.api.financialsupport.repository;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
@ActiveProfiles("local")
public class FinancialSupportRepositoryTest extends DefaultTest {

    @Autowired
    FinancialSupportRepository financialSupportRepository;

    @Test
    public void saveTest() {

        FinancialSupport financialSupport = financialSupportRepository.save(getDefaultTestFinancialSupport());

        assertThat(financialSupport.getLocalGovernment().getCode()).isEqualTo("REG" + testId);
        assertThat(financialSupportRepository.findById(testId).get()).isEqualTo(financialSupport);

    }

    @Test
    public void findAllTest() {

        long id = 7777L;
        long id2 = 8888L;

        String name = "test1";
        String name2 = "test2";

        FinancialSupport testFinancialSupport1 = getTestFinancialSupportWith(id, name);
        FinancialSupport testFinancialSupport2 = getTestFinancialSupportWith(id2, name2);

        financialSupportRepository.save(testFinancialSupport1);
        financialSupportRepository.save(testFinancialSupport2);

        List<FinancialSupport> financialSupportList = financialSupportRepository.findAll();

        assertThat(financialSupportList.size()).isEqualTo(2);
        assertThat(financialSupportList.get(0).getId()).isEqualTo(id);
        assertThat(financialSupportList.get(0).getLocalGovernment().getName()).isEqualTo(name);

    }

    @Test
    public void findFinancialSupportByRegionNameTest() {

        FinancialSupport insertedFinancialSupport = financialSupportRepository.save(getDefaultTestFinancialSupport());
        FinancialSupport searchFinancialSupport = financialSupportRepository.findFinancialSupportByRegionName(insertedFinancialSupport.getLocalGovernment().getName());

        assertThat(searchFinancialSupport).isEqualTo(insertedFinancialSupport);

    }
}