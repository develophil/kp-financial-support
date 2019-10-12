package com.kakaopay.hkp.lgs.api.account.domain.entity;

import com.kakaopay.hkp.lgs.api.account.domain.enums.AuthorityName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 15)
    @Id
    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    public Authority(@NotNull @Size(max = 15) AuthorityName name) {
        this.name = name;
    }

    public String getNameStr() {
        return this.name.name();
    }
}
