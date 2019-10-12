package com.kakaopay.hkp.lgs.api.account.service;

import com.kakaopay.hkp.lgs.api.account.domain.entity.User;
import com.kakaopay.hkp.lgs.api.account.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        return userRepository.findOneWithAuthoritiesByUsername(login)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getNameStr()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(),
            grantedAuthorities);
    }
}
