package com.kakaopay.hkp.lgs.api.financialsupport.repository;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    @Test
    public void modifyFinancialSupportTest() {

        //given
        String modifiedRegion = "변경지자체";
        String modifiedRate = "1.11%~9.99%";
        String modifiedLimit = "99억원 이내";
        FinancialSupport original = getDefaultTestFinancialSupport();
        FinancialSupportDto modifying = FinancialSupportDto.builder()
                .region(modifiedRegion)
                .rate(modifiedRate)
                .limit(modifiedLimit)
                .build();

        //when
        FinancialSupport target = financialSupportRepository.save(original);
        target.modify(modifying);
        financialSupportRepository.saveAndFlush(target);

        FinancialSupport modified = financialSupportRepository.findById(original.getId()).get();

        //then
        Assertions.assertThat(modified.getId()).isEqualTo(original.getId());
        Assertions.assertThat(modified.getLocalGovernment().getName()).isEqualTo(modifiedRegion);
        Assertions.assertThat(modified.getSupportLimit()).isEqualTo(modifying.getLimit());
        Assertions.assertThat(modified.getInterestDifferenceSupportRatio()).isEqualTo(modifying.getRate());
        Assertions.assertThat(modified.getSupportTarget()).isEqualTo(original.getSupportTarget());
        Assertions.assertThat(modified.getReferrelInstitute()).isEqualTo(original.getReferrelInstitute());
        Assertions.assertThat(modified.getReceptionBranch()).isEqualTo(original.getReceptionBranch());
        Assertions.assertThat(modified.getManagementBranch()).isEqualTo(original.getManagementBranch());
        Assertions.assertThat(modified.getUsage()).isEqualTo(original.getUsage());
        Assertions.assertThat(modified.getUpdateDateTime()).isNotEqualTo(original.getUpdateDateTime());
    }

    @Test
    public void 지원한도컬럼에서_지원금액으로_내림차순정렬_후_특정_개수만큼_출력_테스트() {

        long id = 7777L;
        long id2 = 8888L;

        String name = "test1";
        String name2 = "test2";

        FinancialSupport testFinancialSupport1 = getTestFinancialSupportWith(id, name);
        FinancialSupport testFinancialSupport2 = getTestFinancialSupportWith(id2, name2);

        testFinancialSupport1.setSupportLimit("2억원 이내");
        testFinancialSupport2.setSupportLimit("10억원 이내");

        financialSupportRepository.save(testFinancialSupport1);
        financialSupportRepository.save(testFinancialSupport2);

        Pageable pageable = PageRequest.of(0,4, Sort.by(Sort.Order.desc("supportLimit.supportLimitExponent"), Sort.Order.asc("interestDifferenceSupportRatio.interestDifferenceSupportToRatio")));

        Page<FinancialSupport> result = financialSupportRepository.findAll(pageable);

        Assertions.assertThat(result.getSize()).isEqualTo(2);
    }
}
