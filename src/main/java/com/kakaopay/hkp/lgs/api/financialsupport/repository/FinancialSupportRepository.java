package com.kakaopay.hkp.lgs.api.financialsupport.repository;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialSupportRepository extends JpaRepository<FinancialSupport, Long> {

    @Query("select FS from FinancialSupport FS where FS.localGovernment.name = :region")
    FinancialSupport findFinancialSupportByRegionName(@Param("region") String region);

}