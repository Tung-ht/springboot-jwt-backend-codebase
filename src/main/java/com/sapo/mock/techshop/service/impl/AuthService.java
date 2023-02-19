package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.common.constant.HttpStatusConstant;
import com.sapo.mock.techshop.common.constant.JwtConstant;
import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.common.utils.DTOValidator;
import com.sapo.mock.techshop.common.utils.DateTimeUtils;
import com.sapo.mock.techshop.dto.request.LoginRequest;
import com.sapo.mock.techshop.dto.response.LoginUserInfo;
import com.sapo.mock.techshop.entity.AuthUser;
import com.sapo.mock.techshop.repository.AuthUserRepo;
import com.sapo.mock.techshop.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private DTOValidator validator;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthUserRepo authUserRepo;

    public LoginUserInfo handleLoginRequest(LoginRequest loginRequest) {
        validator.validate(loginRequest);

        String username = loginRequest.getUsername();
        String requestedPassword = loginRequest.getPassword();
        // check username
        AuthUser authUser = authUserService.findByUsername(username);
        if (authUser == null) { // username not found
            throw new BusinessException(HttpStatusConstant.AUTHENTICATION_FAIL_CODE, HttpStatusConstant.AUTHENTICATION_FAIL_MESSAGE);
        } else {
            // check password
            if (checkPassword(requestedPassword, authUser.getPassword())) {
                authUser.setLastLoginAt(DateTimeUtils.getNow());
                authUserRepo.save(authUser);
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return loginSuccess(authUser);
            }
            else // password not correct
                throw new BusinessException(HttpStatusConstant.AUTHENTICATION_FAIL_CODE, HttpStatusConstant.AUTHENTICATION_FAIL_MESSAGE);
        }
    }

    private boolean checkPassword(String requestedPassword, String password) {
        return requestedPassword.equals(password);
    }

    private LoginUserInfo loginSuccess(AuthUser authUser) {
        String accessToken = JwtTokenService.buildJWT(authUser.getUsername(), JwtConstant.EXPIRATION_TIME);
        String refreshToken = JwtTokenService.buildJWT(authUser.getUsername(), JwtConstant.REFRESH_TOKEN_EXP_TIME);
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                authUser.getUsername(),
                authUser.getRole(),
                accessToken,
                refreshToken
        );
        return loginUserInfo;
    }
}
