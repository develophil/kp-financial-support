package com.kakaopay.hkp.lgs.api.account.domain.dto;


import com.kakaopay.hkp.lgs.base.domain.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto extends BaseParam {

    private static final long serialVersionUID = -3598503665945336675L;

    private String id;
    private String pw;
}
