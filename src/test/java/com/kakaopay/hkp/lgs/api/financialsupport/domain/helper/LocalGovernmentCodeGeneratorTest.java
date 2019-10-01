package com.kakaopay.hkp.lgs.api.financialsupport.domain.helper;

import com.kakaopay.hkp.lgs.exception.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
public class LocalGovernmentCodeGeneratorTest {

    @Test
    public void generateCodeTest() {
        assertThatThrownBy(() -> LocalGovernmentCodeGenerator.generateCode(-1))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("id");
        assertThatThrownBy(() -> LocalGovernmentCodeGenerator.generateCode(10000))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("id");
        assertThat(LocalGovernmentCodeGenerator.generateCode(0)).isEqualTo(LocalGovernmentCodeGenerator.REGISTER_CODE_PREFIX + "0000");
        assertThat(LocalGovernmentCodeGenerator.generateCode(9999)).isEqualTo(LocalGovernmentCodeGenerator.REGISTER_CODE_PREFIX + "9999");
    }
}
