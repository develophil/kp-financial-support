package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request.RegionDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportService;
import com.kakaopay.hkp.lgs.base.controller.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "지원하는 지자체명을 입력 받아 해당 지자체의 지원정보를 출력하는 API")
    @GetMapping("/by/region")
    public FinancialSupportDto findFinancialSupportByRegion(
            @ApiParam(name = "region", value = "검색할 지자체 명", required = true)
            @RequestBody RegionDto regionDto) {
        return FinancialSupportDto.from(financialSupportService.findFinancialSupportByRegionDto(regionDto));
    }

    @ApiOperation(value = "지원하는 지자체 정보 수정 기능 API")
    @PatchMapping("/{id}")
    public ResponseEntity<FinancialSupportDto> modifyFinancialSupport(
            @PathVariable("id") FinancialSupport financialSupport,
            @RequestBody FinancialSupportDto financialSupportDto) {
        return new ResponseEntity<>(
                FinancialSupportDto.from(financialSupportService.modifyFinancialSupport(financialSupport, financialSupportDto))
                , HttpStatus.CREATED
        );
    }
}
