package com.kakaopay.hkp.lgs.api.financialsupport.service;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.repository.FinancialSupportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FinancialSupportSearchService {

    private final FinancialSupportRepository financialSupportRepository;

    public FinancialSupportSearchService(FinancialSupportRepository financialSupportRepository) {
        this.financialSupportRepository = financialSupportRepository;
    }

    public Set<String> findHigherLimitByPaging(Pageable pageable) {

        Page<FinancialSupport> list = financialSupportRepository.findAll(pageable);

        return list.getContent().stream().map(FinancialSupport::getReferrelInstitute).collect(Collectors.toSet());
    }

    public String findInstituteLeastRate() {

        PageRequest pageable = PageRequest.of(0,
                1,
                Sort.by(Sort.Order.asc("interestDifferenceSupportRatio.interestDifferenceSupportToRatio")));

        Page<FinancialSupport> list = financialSupportRepository.findAll(pageable);

        return list.getContent().stream().map(FinancialSupport::getReferrelInstitute).findFirst().get();
    }
}
