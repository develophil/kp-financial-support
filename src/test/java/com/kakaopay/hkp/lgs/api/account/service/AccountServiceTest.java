package com.kakaopay.hkp.lgs.api.account.service;

import com.kakaopay.hkp.lgs.api.account.DefaultAccountTest;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.repository.UserRepository;
import com.kakaopay.hkp.lgs.security.jwt.JWTFilter;
import com.kakaopay.hkp.lgs.security.jwt.TokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AccountServiceTest extends DefaultAccountTest {


    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private TokenProvider tokenProvider;

    @Test
    public void signUp() {

        //given
        UserDto userDto = createDefaultTestUserDto();

        given(tokenProvider.createToken(any(UsernamePasswordAuthenticationToken.class))).willReturn(newToken);

        //when
        String token = accountService.signUp(userDto);

        //then
        Assertions.assertThat(token).isEqualTo(newToken);
    }

    @Test(expected = EntityExistsException.class)
    public void signUp_exist_user() {

        //given
        UserDto userDto = createTestUserDtoWithEncodedPw("signup_exist", testPassword);
        saveTestUser(userDto);

        given(tokenProvider.createToken(any(UsernamePasswordAuthenticationToken.class))).willReturn(newToken);

        //when
        accountService.signUp(userDto);

        //then
    }

    @Test
    public void signIn() {

        //given
        UserDto userDto = createTestUserDtoWithEncodedPw("signin", testPassword);
        saveTestUser(userDto);

        given(tokenProvider.createToken(any(UsernamePasswordAuthenticationToken.class))).willReturn(newToken);

        //when
        String token = accountService.signIn(userDto);

        //then
        Assertions.assertThat(token).isEqualTo(newToken);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void signIn_not_exist_user() {

        //given
        UserDto userDto = new UserDto("signin_not_exist", "notExist");

        //when
        String token = accountService.signIn(userDto);

        //then
    }

    @Test(expected = BadCredentialsException.class)
    public void signIn_invalid_auth() {

        //given
        String username = "signin_invalid";
        UserDto validUserDto = new UserDto(username, "valid");
        UserDto invalidUserDto = new UserDto(username, "invalid");
        saveTestUser(validUserDto);

        //when
        String token = accountService.signIn(invalidUserDto);

        //then
    }

    @Test
    public void refresh() {

        //given
        String requestToken = JWTFilter.TOKEN_BEARER + originalToken;
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);

        given(tokenProvider.getAuthentication(originalToken)).willReturn(authentication);
        given(tokenProvider.createToken(authentication)).willReturn(newToken);

        //when
        String refreshToken = accountService.refresh(requestToken);

        //then
        Assertions.assertThat(refreshToken).isEqualTo(newToken);
    }

    @Test
    public void splitToken() throws Exception {
        String requestToken = JWTFilter.TOKEN_BEARER + "token";
        String token = accountService.splitToken(requestToken);
        Assertions.assertThat(token).isEqualTo("token");
    }

    @Test(expected = IllegalArgumentException.class)
    public void splitToken_no_type() throws Exception {
        String requestToken = "token";
        accountService.splitToken(requestToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void splitToken_empty() throws Exception {
        String requestToken = "";
        accountService.splitToken(requestToken);
    }

    private void saveTestUser(UserDto userDto) {
        userRepository.save(createTestUserWithAuthority(userDto.getId(), userDto.getPw()));
    }
}