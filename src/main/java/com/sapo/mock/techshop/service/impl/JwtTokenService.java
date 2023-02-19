package com.sapo.mock.techshop.service.impl;

import com.sapo.mock.techshop.common.constant.HttpStatusConstant;
import com.sapo.mock.techshop.common.constant.JwtConstant;
import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.dto.response.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;

@Slf4j
public class JwtTokenService {
    public static String buildJWT(String username, long expireTime) {
        String Jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET_KEY)
                .compact();
        return Jwt;
    }

    public static Authentication parseRefreshToken(String refreshToken) {
        if (refreshToken == null) {
            return null;
        }
        String username = Jwts.parser()
                .setSigningKey(JwtConstant.SECRET_KEY)
                .parseClaimsJws(refreshToken.replace(JwtConstant.PREFIX_TOKEN, ""))
                .getBody()
                .getSubject();
        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) :
                null;
    }

    public static Authentication parseTokenToUserInformation(HttpServletRequest request) {
        String token = request.getHeader(JwtConstant.AUTHORIZATION);
        if (token == null) {
            return null;
        }
        String username = Jwts.parser()
                .setSigningKey(JwtConstant.SECRET_KEY)
                .parseClaimsJws(token.replace(JwtConstant.PREFIX_TOKEN, ""))
                .getBody()
                .getSubject();
        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) :
                null;
    }

    public static String getUsernameFromJwt(String token) {
        String username = Jwts.parser()
                .setSigningKey(JwtConstant.SECRET_KEY)
                .parseClaimsJws(token.replace(JwtConstant.PREFIX_TOKEN, ""))
                .getBody()
                .getSubject();
        return username;
    }

    public static LoginUserInfo getUserInfoFromToken(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConstant.SECRET_KEY)
                    .parseClaimsJws(token.replace(JwtConstant.PREFIX_TOKEN, ""))
                    .getBody();

            var loginUserInfoDTO = LoginUserInfo.builder().username(claims.getSubject()).build();
            return loginUserInfoDTO;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new BusinessException(HttpStatusConstant.UNAUTHORIZED_CODE, HttpStatusConstant.UNAUTHORIZED_MESSAGE);
        }
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JwtConstant.SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
