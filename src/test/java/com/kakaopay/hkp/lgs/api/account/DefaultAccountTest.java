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

    protected UserDto createDefaultTestUserDtoWithEncodedPw(){
        return createTestUserDtoWithEncodedPw(testUsername, testPassword);
    }
    protected UserDto createTestUserDtoWithEncodedPw(String username, String password){
        return new UserDto(username, encode(password));
    }

    protected User createDefaultTestUserWithAuthority(){
        return new User(createDefaultTestUserDtoWithEncodedPw());
    }

    protected User createTestUserWithAuthority(String username, String password){
        return new User(createTestUserDtoWithEncodedPw(username, password));
    }

    protected String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
