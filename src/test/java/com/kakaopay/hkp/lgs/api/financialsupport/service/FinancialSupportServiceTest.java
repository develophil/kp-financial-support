package com.kakaopay.hkp.lgs.api.financialsupport.service;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.controller.FinancialSupportController;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.repository.FinancialSupportRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

@SpringBootTest
public class FinancialSupportServiceTest extends DefaultTest {

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
}