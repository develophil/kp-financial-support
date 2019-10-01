package com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.base.domain.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FinancialSupportDto extends BaseModel {

    private static final long serialVersionUID = -1284408260616419023L;

    private String region;
    private String target;
    private String usage;
    private String limit;
    private String rate;
    private String institute;
    private String mgmt;
    private String reception;

    public static FinancialSupportDto from(FinancialSupport financialSupport) {
        return FinancialSupportDto.builder()
                .region(financialSupport.getLocalGovernment().getName())
                .target(financialSupport.getSupportTarget())
                .usage(financialSupport.getUsage())
                .limit(financialSupport.getSupportLimitAmount())
                .rate(financialSupport.getInterestDifferenceSupportRatio())
                .institute(financialSupport.getReferrelInstitute())
                .mgmt(financialSupport.getManagementBranch())
                .reception(financialSupport.getReceptionBranch())
                .build();
    }

}
