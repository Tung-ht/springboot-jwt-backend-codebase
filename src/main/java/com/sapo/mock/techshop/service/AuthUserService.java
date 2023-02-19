package com.sapo.mock.techshop.service;

import com.sapo.mock.techshop.dto.request.RegisterUserRequest;
import com.sapo.mock.techshop.entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService {
    AuthUser findByUsername(String username);
    AuthUser createUser(RegisterUserRequest registerUserRequest);
}
