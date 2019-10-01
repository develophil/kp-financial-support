package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.helper.RateCompositor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InterestDifferenceSupportRatio {

    @Column(name = "support_from_rate", length = 5)
    private String interestDifferenceSupportFromRatio;

    @Column(name = "support_to_rate", length = 5)
    private String interestDifferenceSupportToRatio;

    public InterestDifferenceSupportRatio(String rate) {
        if (Strings.isNotEmpty(rate)) {
            this.setRateString(rate);
        }
    }

    public String toRateString() {
        return RateCompositor.getInterestDifferenceSupportRatio(this);
    }

    public void setRateString(String rateStr) {
        RateCompositor.setSplitRate(this, rateStr);
    }
}
