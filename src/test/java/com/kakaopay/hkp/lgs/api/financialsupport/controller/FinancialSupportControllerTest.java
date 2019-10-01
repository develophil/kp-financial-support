package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FinancialSupportController.class)
public class FinancialSupportControllerTest extends DefaultTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FinancialSupportService financialSupportService;

    private List<FinancialSupportDto> financialSupportDtoList;
    private List<FinancialSupport> financialSupportList;

    @Before
    public void setUp() throws Exception {
        financialSupportDtoList = getDefaultTestFinancialSupportDtoList();
        financialSupportList = getDefaultTestFinancialSupportList();
    }

    @Test
    public void 지원하는_모든_지자체목록_조회_호출_테스트() throws Exception {

        //given
        given(financialSupportService.findAllFinancialSupportList()).willReturn(financialSupportList);

        //when
        mvc.perform(get("/api/financial-supports")).andDo(print())

        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].region").value(testRegion))
        .andExpect(jsonPath("$[1].region").value(testRegion2))
        .andExpect(handler().handlerType(FinancialSupportController.class))
        .andExpect(handler().methodName("findAllFinancialSupportList"))
        .andReturn();

    }
}