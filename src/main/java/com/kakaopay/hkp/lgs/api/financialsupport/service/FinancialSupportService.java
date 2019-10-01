package com.kakaopay.hkp.lgs.api.financialsupport.service;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.request.RegionDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.response.FinancialSupportDto;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.repository.FinancialSupportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialSupportService {

    private final FinancialSupportRepository financialSupportRepository;

    public FinancialSupportService(FinancialSupportRepository financialSupportRepository) {
        this.financialSupportRepository = financialSupportRepository;
    }

    public List<FinancialSupport> findAllFinancialSupportList() {
        return financialSupportRepository.findAll();
    }

    public FinancialSupport findFinancialSupportByRegionDto(RegionDto dto) {
        return financialSupportRepository.findFinancialSupportByRegionName(dto.getRegion());
    }

    public FinancialSupport modifyFinancialSupport(FinancialSupport original, FinancialSupportDto modifying) {
        return financialSupportRepository.saveAndFlush(original.modify(modifying));
    }
}
