package com.kakaopay.hkp.lgs.api.account.controller;

import com.kakaopay.hkp.lgs.api.account.DefaultAccountTest;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.service.AccountService;
import com.kakaopay.hkp.lgs.security.jwt.JWTFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest extends DefaultAccountTest {

    @Value("${url.api.account.signup}")
    private String signupUrl;

    @Value("${url.api.account.signin}")
    private String signinUrl;

    @Value("${url.api.account.refresh}")
    private String refreshUrl;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void signUp() throws Exception {

        //given
        String testToken = "testToken";
        given(accountService.signUp(any(UserDto.class))).willReturn(testToken);

        //when
        mvc.perform(post(signupUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(createDefaultTestUserDto()))
        )

        //then
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.token").value(testToken))
        .andExpect(handler().handlerType(AccountController.class))
        .andExpect(handler().methodName("signUp"))
        .andReturn();
    }

    @Test
    public void signIn() throws Exception {

        //given
        String testToken = "testToken";
        given(accountService.signIn(any(UserDto.class))).willReturn(testToken);

        //when
        mvc.perform(post(signinUrl)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(toJson(createDefaultTestUserDto()))
        )

        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").value(testToken))
        .andExpect(handler().handlerType(AccountController.class))
        .andExpect(handler().methodName("signIn"))
        .andReturn();
    }

    @Test
    public void refreshToken() throws Exception {

        //given
        String testToken = "testToken";
        String testAnotherToken = "testAnotherToken";
        given(accountService.refresh(testToken)).willReturn(testAnotherToken);

        //when
        mvc.perform(post(refreshUrl)
                .header(JWTFilter.AUTHORIZATION_HEADER, testToken)
        )

        //then
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.token").value(testAnotherToken))
        .andExpect(handler().handlerType(AccountController.class))
        .andExpect(handler().methodName("refreshToken"))
        .andReturn();
    }
}
