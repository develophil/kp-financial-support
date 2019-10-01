package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportService;
import com.kakaopay.hkp.lgs.base.controller.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "FinancialSupportController")
@RequestMapping("/api/financial-supports")
public class FinancialSupportController extends BaseApiController {

    private final FinancialSupportService financialSupportService;

    public FinancialSupportController(FinancialSupportService financialSupportService) {
        this.financialSupportService = financialSupportService;
    }

    @ApiOperation(value = "지원하는 지자체 목록 검색 API - 지원정보 포함")
    @GetMapping("")
    public List<FinancialSupportDto> findAllFinancialSupportList() {
        return financialSupportService.findAllFinancialSupportList().stream()
                .map(FinancialSupportDto::from)
                .collect(Collectors.toList());
    }

}
