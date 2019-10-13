package com.kakaopay.hkp.lgs.api.account;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.domain.entity.User;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Ignore
public class DefaultAccountTest extends DefaultTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected final String testUsername = "testUser";
    protected final String testPassword = "testPw";

    protected final String originalToken = "oToken";
    protected final String newToken = "newToken";

    protected UserDto createDefaultTestUserDto(){
        return new UserDto(testUsername, testPassword);
    }

    protected UserDto createDefaultTestUserWithEncodedPwDto(){
        return new UserDto(testUsername, passwordEncoder.encode(testPassword));
    }

    protected User createDefaultTestUserWithAuthority(){
        return new User(createDefaultTestUserWithEncodedPwDto());
    }
}
