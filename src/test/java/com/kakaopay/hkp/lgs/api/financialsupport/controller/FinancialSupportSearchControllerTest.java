package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportSearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FinancialSupportSearchController.class)
public class FinancialSupportSearchControllerTest extends DefaultTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FinancialSupportSearchService financialSupportSearchService;

    private Set<String> result;

    @Before
    public void setUp() throws Exception {
        result = new HashSet<>(Arrays.asList(testRegion, testRegion2));
    }

    @Test
    public void findHigherLimitByPagingTest() throws Exception {

        Pageable pageable = PageRequest.of(0,4, Sort.by(Sort.Order.desc("supportLimit.supportLimitExponent"), Sort.Order.asc("interestDifferenceSupportRatio.interestDifferenceSupportToRatio")));

        //given
        given(financialSupportSearchService.findHigherLimitByPaging(pageable)).willReturn(result);

        //when
        mvc.perform(get("/api/financial-supports/search/regions/higher-limit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"size\":\"4\"}")
        ).andDo(print())

        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists())
        .andExpect(handler().handlerType(FinancialSupportSearchController.class))
        .andExpect(handler().methodName("findHigherLimitByPaging"))
        .andReturn();

    }
}
