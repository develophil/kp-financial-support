package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.FinancialSupportCsv;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "financial_support")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
public class FinancialSupport extends AbstractAuditingEntity {

    private static final long serialVersionUID = -5612771616185909596L;

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "code", nullable = false)
    private LocalGovernment localGovernment;

    @Column(name = "support_target", length = 150)
    private String supportTarget;

    @Column(name = "usage", length = 20)
    private String usage;

    @Embedded
    private SupportLimit supportLimit;

    @Embedded
    private InterestDifferenceSupportRatio interestDifferenceSupportRatio;

    @Column(name = "institute", length = 100)
    private String referrelInstitute;

    @Column(name = "management", length = 50)
    private String managementBranch;

    @Column(name = "reception", length = 50)
    private String receptionBranch;

    public String getInterestDifferenceSupportRatio() {
        return interestDifferenceSupportRatio.toRateString();
    }
    public void setInterestDifferenceSupportRatio(String rate) {
        this.interestDifferenceSupportRatio = new InterestDifferenceSupportRatio(rate);
    }

    public String getSupportLimit() {
        return supportLimit.toLimitAmountString();
    }

    public void setSupportLimit(String limit) {
        this.supportLimit = new SupportLimit(limit);
    }

    @Builder
    public FinancialSupport(Long id, LocalGovernment localGovernment, String supportTarget, String usage, SupportLimit supportLimit, InterestDifferenceSupportRatio interestDifferenceSupportRatio, String referrelInstitute, String managementBranch, String receptionBranch) {
        this.id = id;
        this.localGovernment = localGovernment;
        this.supportTarget = supportTarget;
        this.usage = usage;
        this.supportLimit = supportLimit;
        this.interestDifferenceSupportRatio = interestDifferenceSupportRatio;
        this.referrelInstitute = referrelInstitute;
        this.managementBranch = managementBranch;
        this.receptionBranch = receptionBranch;
    }

    public FinancialSupport(FinancialSupportCsv bean) {

        long id = bean.getId();

        this.id = id;
        this.localGovernment = new LocalGovernment(id, bean.getRegion());
        this.setSupportLimit(bean.getLimit());
        this.setInterestDifferenceSupportRatio(bean.getRate());
        this.managementBranch = bean.getMgmt();
        this.receptionBranch = bean.getReception();
        this.referrelInstitute = bean.getInstitute();
        this.supportTarget = bean.getTarget();
        this.usage = bean.getUsage();

    }

    public FinancialSupport modify(FinancialSupportDto modifying) {

        Optional.ofNullable(modifying.getRegion()).ifPresent((v) -> this.localGovernment = new LocalGovernment(id, v));
        Optional.ofNullable(modifying.getRate()).ifPresent(this::setInterestDifferenceSupportRatio);
        Optional.ofNullable(modifying.getMgmt()).ifPresent((v) -> this.managementBranch = v);
        Optional.ofNullable(modifying.getReception()).ifPresent((v) -> this.receptionBranch = v);
        Optional.ofNullable(modifying.getInstitute()).ifPresent((v) -> this.referrelInstitute = v);
        Optional.ofNullable(modifying.getLimit()).ifPresent(this::setSupportLimit);
        Optional.ofNullable(modifying.getTarget()).ifPresent((v) -> this.supportTarget = v);
        Optional.ofNullable(modifying.getUsage()).ifPresent((v) -> this.usage = v);
        this.updateDateTime = LocalDateTime.now();

        return this;
    }
}
