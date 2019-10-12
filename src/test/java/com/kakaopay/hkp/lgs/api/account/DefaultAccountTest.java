package com.kakaopay.hkp.lgs.api.account;

import com.kakaopay.hkp.lgs.DefaultTest;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.domain.entity.User;
import org.junit.Ignore;

@Ignore
public class DefaultAccountTest extends DefaultTest {

    protected static final String testUsername = "testUser";
    protected static final String testPassword = "testPw";

    protected static User createDefaultTestUserWithAuthority(){
        return new User(new UserDto(testUsername, testPassword));
    }
}
