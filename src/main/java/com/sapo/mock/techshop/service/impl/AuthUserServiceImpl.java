package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.dto.request.RegisterUserRequest;
import com.sapo.mock.techshop.entity.AuthUser;
import com.sapo.mock.techshop.repository.AuthUserRepo;
import com.sapo.mock.techshop.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    private AuthUserRepo authUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepo.findAuthUserByUsername(username);
        if (user == null) {
            log.debug("username: \"" + username + "\" doesn't exist");
            // RestAuthenticationEntryPoint will handle it
            throw new UsernameNotFoundException("Username: \"" + username + "\" doesn't exist");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepo.findAuthUserByUsername(username);
    }

    @Override
    public AuthUser createUser(RegisterUserRequest registerUserRequest) {
        return null;
    }
}
