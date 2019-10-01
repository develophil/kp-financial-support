package com.kakaopay.hkp.lgs.api.financialsupport.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kakaopay.hkp.lgs.base.domain.BaseModel;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString
@JsonPropertyOrder({ "id", "region", "target", "usage", "limit", "rate", "institute", "mgmt", "reception" })
public class FinancialSupportCsv extends BaseModel {

    private static final long serialVersionUID = 6826010683808379911L;

    private Long id;
    private String region;
    private String target;
    private String usage;
    private String limit;
    private String rate;
    private String institute;
    private String mgmt;
    private String reception;
}
