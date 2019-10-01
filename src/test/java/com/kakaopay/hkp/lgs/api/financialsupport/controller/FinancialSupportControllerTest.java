package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request.RegionDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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


    @Test
    public void 지자체명으로_지원정보_조회_호출_테스트() throws Exception {

        RegionDto regionDto = new RegionDto(testRegion);

        //given
        given(financialSupportService.findFinancialSupportByRegionDto(regionDto)).willReturn(getDefaultTestFinancialSupport());

        //when
        mvc.perform(
                get("/api/financial-supports/by/region")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"region\":\"" + testRegion + "\"}")
        ).andDo(print())

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.region").value(testRegion))
                .andExpect(jsonPath("$.target").exists())
                .andExpect(jsonPath("$.usage").exists())
                .andExpect(jsonPath("$.limit").exists())
                .andExpect(jsonPath("$.rate").exists())
                .andExpect(jsonPath("$.institute").exists())
                .andExpect(jsonPath("$.mgmt").exists())
                .andExpect(jsonPath("$.reception").exists())
                .andExpect(handler().handlerType(FinancialSupportController.class))
                .andExpect(handler().methodName("findFinancialSupportByRegion"))
                .andReturn();

    }
//
//    @Test
//    public void 지자체정보_정상_수정_테스트() throws Exception {
//
//        //given
////        given().willReturn();
//
//        //when
//        mvc.perform(post("/api/financial-supports/file/load-support-data")).andDo(print())
//
//        //then
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.successCount").value(successCountDto.getSuccessCount()))
//        .andExpect(handler().handlerType(FinancialSupportFileController.class))
//        .andExpect(handler().methodName("modifyFinancialSupport"))
//        .andReturn();
//    }
}