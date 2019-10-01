package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.SuccessCountDto;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinanceSupportFileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FinancialSupportFileController.class)
public class FinancialSupportFileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FinanceSupportFileService financeSupportFileService;

    private int returnSuccessCount = 10;

    private SuccessCountDto successCountDto;
    private MockMultipartFile csvFile;

    @Before
    public void setUp() throws Exception {
        successCountDto = new SuccessCountDto(returnSuccessCount);
        csvFile = new MockMultipartFile("file", "test.csv", "text/plain", "test content".getBytes());
    }

    @Test
    public void 지자체지원정보_저장된CSV_저장_테스트() throws Exception {

        //given
        given(financeSupportFileService.insertPathCsvData()).willReturn(returnSuccessCount);

        //when
        mvc.perform(post("/api/financial-supports/file/load-support-data")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())

        //then
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.successCount").value(successCountDto.getSuccessCount()))
            .andReturn();
    }

    @Test
    public void 지자체지원정보_업로드된CSV_저장_테스트() throws Exception {

        //given
        given(financeSupportFileService.insertUploadCsvData(csvFile)).willReturn(returnSuccessCount);

        //when
        mvc.perform(MockMvcRequestBuilders.multipart("/api/financial-supports/file/load-support-data")
                .file(csvFile))
                .andDo(print())

        //then
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.successCount").value(successCountDto.getSuccessCount()))
            .andReturn();

        verify(financeSupportFileService).insertUploadCsvData(csvFile);
    }
}
