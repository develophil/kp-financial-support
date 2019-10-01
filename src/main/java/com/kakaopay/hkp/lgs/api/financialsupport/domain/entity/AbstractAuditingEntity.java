package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 6917139824636603164L;

    @CreatedDate
    @Column(name = "create_dt", nullable = false)
    @JsonIgnore
    protected LocalDateTime createDateTime = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "update_dt")
    @JsonIgnore
    protected LocalDateTime updateDateTime = LocalDateTime.now();

}
