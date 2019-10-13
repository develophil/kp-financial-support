package com.kakaopay.hkp.lgs.api.account.domain.dto;

import com.kakaopay.hkp.lgs.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTToken extends BaseModel {

    private static final long serialVersionUID = -6491196672472142917L;

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }
}
