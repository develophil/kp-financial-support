package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.helper.LimitCompositor;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class SupportLimit {

    @Column(name = "support_limit_exponent")
    private Integer supportLimitExponent;

    @Column(name = "support_limit_digits")
    private Integer supportLimitDigits;

    public SupportLimit(String limit) {
        if (Strings.isNotEmpty(limit)) {
            this.setSupportLimit(limit);
        }
    }


    public boolean isFixedAmount() {
        return this.supportLimitExponent != 0;
    }

    public String toLimitAmountString() {
        return LimitCompositor.toLimitAmountString(this);
    }

    public void setSupportLimit(String limit) {
        LimitCompositor.setSupportLimit(this, limit);
    }
}
