package com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response;

import lombok.Getter;

@Getter
public class SuggestInstituteDto {
    private String suggestInstitute;

    public SuggestInstituteDto(String suggestInstitute) {
        this.suggestInstitute = suggestInstitute;
    }
}
