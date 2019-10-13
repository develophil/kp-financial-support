package com.kakaopay.hkp.lgs.api.account.service;

import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.domain.entity.User;
import com.kakaopay.hkp.lgs.api.account.repository.UserRepository;
import com.kakaopay.hkp.lgs.security.jwt.TokenProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@Service
@Transactional
public class AccountService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AccountService(UserRepository userRepository, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    public String signUp(UserDto userDto) {

        if (userRepository.existsUserByUsername(userDto.getId())) {
            throw new EntityExistsException("중복");
        }

        String rawPassword = userDto.getPw();

        userDto.setPw(passwordEncoder.encode(rawPassword));

        User signupUser = new User(userDto);

        userRepository.save(signupUser);

        return createToken(signupUser.getUsername(), rawPassword);
    }

    public String signIn(UserDto userDto) {

        userRepository.findByUsername(userDto.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return createToken(userDto.getId(), userDto.getPw());
    }

    private String createToken(String username, String rawPassword) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, rawPassword);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    public String refresh(String originalToken) {

        return tokenProvider.createToken(tokenProvider.getAuthentication(splitToken(originalToken)));
    }

    protected String splitToken(String originalToken) {
        String[] splitToken = originalToken.split(StringUtils.SPACE);

        if (splitToken.length != 2) {
            throw new IllegalArgumentException("token이 유효하지 않음.");
        }

        return splitToken[1];
    }
}
