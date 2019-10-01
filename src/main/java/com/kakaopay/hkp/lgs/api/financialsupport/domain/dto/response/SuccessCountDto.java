package com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response;

import lombok.Getter;

@Getter
public class SuccessCountDto {
    private int successCount;

    public SuccessCountDto(int successCount) {
        this.successCount = successCount;
    }
}
