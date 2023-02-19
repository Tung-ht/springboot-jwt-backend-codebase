package com.sapo.mock.techshop.security.handler;

import com.sapo.mock.techshop.common.constant.HttpStatusConstant;
import com.sapo.mock.techshop.dto.response.GeneralResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle UserNotFoundException thrown by AuthUserService, wrong password exception
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        GeneralResponse<?> generalResponse = GeneralResponse.error(HttpStatusConstant.AUTHENTICATION_FAIL_CODE, HttpStatusConstant.AUTHENTICATION_FAIL_MESSAGE);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(generalResponse);
        response.setContentType("application/json");
        response.setStatus(403); // Bad credentials
        response.getOutputStream().println(json);
    }

}
