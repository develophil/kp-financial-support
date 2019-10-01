package com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request;

import com.kakaopay.hkp.lgs.base.domain.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto extends BaseParam {

    private static final long serialVersionUID = -6652276669826907390L;

    private String region;
}
