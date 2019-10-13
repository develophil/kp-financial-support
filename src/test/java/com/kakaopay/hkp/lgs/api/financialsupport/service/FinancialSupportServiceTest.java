package com.kakaopay.hkp.lgs.api.financialsupport.service;

import com.kakaopay.hkp.lgs.api.financialsupport.DefaultFinancialSupportTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request.RegionDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.repository.FinancialSupportRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class FinancialSupportServiceTest extends DefaultFinancialSupportTest {

    @Autowired
    FinancialSupportService financialSupportService;

    @MockBean
    private FinancialSupportRepository financialSupportRepository;

    private List<FinancialSupport> financialSupportList;

    @Before
    public void setUp() throws Exception {
        financialSupportList = getDefaultTestFinancialSupportList();
    }

    @Test
    public void findAllFinancialSupportList() {

        //given
        given(financialSupportRepository.findAll()).willReturn(financialSupportList);

        //when
        List<FinancialSupport> list = financialSupportService.findAllFinancialSupportList();

        //then
        Assertions.assertThat(list.size()).isEqualTo(financialSupportList.size());
        Assertions.assertThat(list.get(0).getLocalGovernment().getName()).isEqualTo(financialSupportList.get(0).getLocalGovernment().getName());
    }

    @Test
    public void findFinancialSupportByRegionDtoTest() {

        //given
        RegionDto regionDto = new RegionDto(testRegion);
        given(financialSupportRepository.findFinancialSupportByRegionName(testRegion)).willReturn(getDefaultTestFinancialSupport());

        //when
        FinancialSupport searched = financialSupportService.findFinancialSupportByRegionDto(regionDto);

        //then
        Assertions.assertThat(searched.getId()).isEqualTo(testId);
        Assertions.assertThat(searched.getLocalGovernment().getName()).isEqualTo(testRegion);

    }
}