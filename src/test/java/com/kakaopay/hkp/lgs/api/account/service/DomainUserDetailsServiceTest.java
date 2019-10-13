package com.kakaopay.hkp.lgs.api.account.service;

import com.kakaopay.hkp.lgs.api.account.DefaultAccountTest;
import com.kakaopay.hkp.lgs.api.account.domain.enums.AuthorityName;
import com.kakaopay.hkp.lgs.api.account.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class DomainUserDetailsServiceTest extends DefaultAccountTest {

    @Autowired
    private DomainUserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUserByUsername_exist() {

        //given
        given(userRepository.findOneWithAuthoritiesByUsername(testUsername)).willReturn(Optional.of(createDefaultTestUserWithAuthority()));

        //when
        UserDetails userDetails = userDetailsService.loadUserByUsername(testUsername);

        //then
        Assertions.assertThat(userDetails.getUsername()).isEqualTo(testUsername);
        Assertions.assertThat(userDetails.getPassword()).isNotEmpty();
        Assertions.assertThat(userDetails.getAuthorities().stream().findFirst().get().getAuthority()).isEqualTo(AuthorityName.ROLE_USER.name());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_not_exist() {
        given(userRepository.findOneWithAuthoritiesByUsername(anyString())).willReturn(Optional.empty());
        userDetailsService.loadUserByUsername("testUsername");
    }
}
