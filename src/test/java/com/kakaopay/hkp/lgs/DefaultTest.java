package com.kakaopay.hkp.lgs;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.InterestDifferenceSupportRatio;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.LocalGovernment;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.SupportLimit;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class DefaultTest {

    protected static final long testId = 9010L;
    protected static final String testRegion = "광명시";
    protected static final String testRegion2 = "강릉시";
    protected static final String testTarget = "광명시 소재 중소기업으로서 광명시장이 추천한 자";
    protected static final String testUsage = "운전";
    protected static final String testLimit = "3억원 이내";
    protected static final String testRate = "2.00%";
    protected static final String testInstitute = "광명시";
    protected static final String testMgmt = "광명지점";
    protected static final String testReception = "전 영업점";

    protected static LocalGovernment getDefaultTestLocalGovernment() {
        return new LocalGovernment(testId, testRegion);
    }

    protected static FinancialSupport getDefaultTestFinancialSupport() {

        return getTestFinancialSupportWith(testRegion);
    }

    protected static FinancialSupport getTestFinancialSupportWith(String region) {

        return getTestFinancialSupportWith(testId, region);
    }

    protected static FinancialSupport getTestFinancialSupportWith(long id, String region) {

        return FinancialSupport.builder()
                .id(id)
                .interestDifferenceSupportRatio(new InterestDifferenceSupportRatio(testRate))
                .localGovernment(new LocalGovernment(id, region))
                .managementBranch(testMgmt)
                .receptionBranch(testReception)
                .referrelInstitute(testInstitute)
                .supportLimit(new SupportLimit(testLimit))
                .supportTarget(testTarget)
                .usage(testUsage)
                .build();
    }

    protected static FinancialSupportDto getTestFinancialSupportDtoWith(String region) {
        return FinancialSupportDto.builder()
                .region(region)
                .target(testTarget)
                .usage(testUsage)
                .limit(testLimit)
                .rate(testRate)
                .institute(testInstitute)
                .mgmt(testMgmt)
                .reception(testReception)
                .build();
    }


    protected static List<FinancialSupportDto> getDefaultTestFinancialSupportDtoList() {
        return Arrays.asList(getTestFinancialSupportDtoWith(testRegion), getTestFinancialSupportDtoWith(testRegion2));
    }

    protected static List<FinancialSupport> getDefaultTestFinancialSupportList() {
        return Arrays.asList(getTestFinancialSupportWith(testRegion), getTestFinancialSupportWith(testRegion2));
    }
}
