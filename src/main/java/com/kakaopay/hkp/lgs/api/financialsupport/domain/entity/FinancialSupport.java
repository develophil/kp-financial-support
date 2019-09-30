package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.FinancialSupportCsv;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "support_limit", length = 20)
    private String supportLimitAmount;

    @Column(name = "support_rate", length = 20)
    private String interestDifferenceSupportRatio;

    @Column(name = "institute", length = 100)
    private String referrelInstitute;

    @Column(name = "management", length = 50)
    private String managementBranch;

    @Column(name = "reception", length = 50)
    private String receptionBranch;

    @Builder
    public FinancialSupport(Long id, LocalGovernment localGovernment, String supportTarget, String usage, String supportLimitAmount, String interestDifferenceSupportRatio, String referrelInstitute, String managementBranch, String receptionBranch) {
        this.id = id;
        this.localGovernment = localGovernment;
        this.supportTarget = supportTarget;
        this.usage = usage;
        this.supportLimitAmount = supportLimitAmount;
        this.interestDifferenceSupportRatio = interestDifferenceSupportRatio;
        this.referrelInstitute = referrelInstitute;
        this.managementBranch = managementBranch;
        this.receptionBranch = receptionBranch;
    }

    public FinancialSupport(FinancialSupportCsv bean) {

        long id = bean.getId();

        this.id = id;
        this.localGovernment = new LocalGovernment(id, bean.getRegion());
        this.interestDifferenceSupportRatio = bean.getRate();
        this.managementBranch = bean.getMgmt();
        this.receptionBranch = bean.getReception();
        this.referrelInstitute = bean.getInstitute();
        this.supportLimitAmount = bean.getLimit();
        this.supportTarget = bean.getTarget();
        this.usage = bean.getUsage();

    }
}
