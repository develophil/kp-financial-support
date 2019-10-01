package com.kakaopay.hkp.lgs.api.financialsupport.controller;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.SuccessCountDto;
import com.kakaopay.hkp.lgs.api.financialsupport.service.FinanceSupportFileService;
import com.kakaopay.hkp.lgs.base.controller.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@Api(value = "FinancialSupportFileController")
@RequestMapping("/api/financial-supports/file")
public class FinancialSupportFileController extends BaseApiController {

    private final FinanceSupportFileService financeSupportFileService;

    public FinancialSupportFileController(FinanceSupportFileService financeSupportFileService) {
        this.financeSupportFileService = financeSupportFileService;
    }

    @ApiOperation(value = "데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value ="/load-support-data", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Object uploadData(
            @ApiParam(name = "file", value = "지자체 지원 정보 csv upload. 파일 첨부하지 않으면 서버에 저장된 디폴트 정보로 저장함.", required = true) @RequestParam(required = false, name = "file") MultipartFile file) {

        int successCount = 0;

        if (Objects.isNull(file)) {
            successCount = financeSupportFileService.insertPathCsvData();
        } else {
            successCount = financeSupportFileService.insertUploadCsvData(file);
        }

        return new ResponseEntity<>(new SuccessCountDto(successCount), HttpStatus.CREATED);
    }
}

