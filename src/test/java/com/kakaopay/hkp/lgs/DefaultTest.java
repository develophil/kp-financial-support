package com.kakaopay.hkp.lgs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Ignore
@RunWith(SpringRunner.class)
public class DefaultTest {

    public static <T> String toJson(T body) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(body);
    }
/*
    @Test
    public void toJsonTest() throws Exception {

        final String id = "test";
        final String pw = "pw";

        String json = toJson(new UserDto(id, pw));

        Assertions.assertThat(json).isEqualTo("{\"id\":\"test\",\"pw\":\"pw\"}");
    }*/
}
