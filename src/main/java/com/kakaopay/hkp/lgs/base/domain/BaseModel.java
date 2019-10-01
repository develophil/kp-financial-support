package com.kakaopay.hkp.lgs.base.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@ToString
@EqualsAndHashCode
public abstract class BaseModel implements Serializable {
}
