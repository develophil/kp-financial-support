package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request.PageDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.SuggestInstituteDto;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinancialSupportSearchService;
import com.kakaopay.hkp.lgs.base.controller.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Api(value = "FinancialSupportController")
@RequestMapping("/api/financial-supports/search")
public class FinancialSupportSearchController extends BaseApiController {


    private final FinancialSupportSearchService financialSupportSearchService;

    public FinancialSupportSearchController(FinancialSupportSearchService financialSupportSearchService) {
        this.financialSupportSearchService = financialSupportSearchService;
    }

    @ApiOperation(value = "지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정개수만 출력하는 API")
    @GetMapping("/regions/higher-limit")
    public Set<String> findHigherLimitByPaging(
            @ApiParam(name = "size", value = "출력갯수", required = true)
            @RequestBody PageDto pageDto
            ) {
        PageRequest pageable = PageRequest.of(0,
                pageDto.getSize(),
                Sort.by(
                        Sort.Order.desc("supportLimit.supportLimitDigits"),
                        Sort.Order.desc("supportLimit.supportLimitExponent"),
                        Sort.Order.asc("interestDifferenceSupportRatio.interestDifferenceSupportToRatio")));

        return financialSupportSearchService.findHigherLimitByPaging(pageable);
    }

    @ApiOperation(value = "이차보전 컬럼에서 보전 비율이 가장 작은 추천 기관명을 출력하는 API")
    @GetMapping("/institute/least-rate")
    public SuggestInstituteDto findInstituteLeastRate() {
        return new SuggestInstituteDto(financialSupportSearchService.findInstituteLeastRate());
    }
}
