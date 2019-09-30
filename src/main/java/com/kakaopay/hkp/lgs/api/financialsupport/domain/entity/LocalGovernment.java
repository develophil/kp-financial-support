package com.kakaopay.hkp.lgs.api.financialsupport.domain.entity;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.helper.LocalGovernmentCodeGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "local_government")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
public class LocalGovernment implements Serializable {

    private static final long serialVersionUID = -2483290161704176801L;

    @Id
    @Column(name = "code", nullable = false, length = 7)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public LocalGovernment(long id, String name) {
        this.code = LocalGovernmentCodeGenerator.generateCode(id);
        this.name = name;
    }
}
