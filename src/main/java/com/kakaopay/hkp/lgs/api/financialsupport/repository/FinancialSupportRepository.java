package com.kakaopay.hkp.lgs.api.financialsupport.repository;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialSupportRepository extends JpaRepository<FinancialSupport, Long> {

}