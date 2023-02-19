package com.sapo.mock.techshop.common.exception.handler;


import com.sapo.mock.techshop.common.constant.HttpStatusConstant;
import com.sapo.mock.techshop.dto.response.GeneralResponse;
import com.sapo.mock.techshop.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler({NullPointerException.class})
    public GeneralResponse<?> handleNullPointerException(NullPointerException nullPointerException) {
        log.error("NullPointerException => rootCause: {}", Arrays.stream(nullPointerException.getStackTrace()).findFirst());
        log.error("NullPointerException => message: {}", nullPointerException.getMessage());
        return GeneralResponse.error(HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_CODE, HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_MESSAGE);
    }

    @ExceptionHandler({SQLException.class})
    public GeneralResponse<?> handleSQLException(SQLException ex) {
        log.error("SQLException => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("SQLException => message: {}", ex.getMessage());
        return GeneralResponse.error(HttpStatusConstant.SQL_CONNECTION_ERROR_CODE, HttpStatusConstant.SQL_CONNECTION_ERROR_MESSAGE);
    }

    @ExceptionHandler(BusinessException.class)
    public GeneralResponse<?> handleBusinessException(BusinessException ex) {
        return GeneralResponse.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public GeneralResponse<?> handleException(Exception ex) {
        log.error("Exception => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("Exception => message: {}", ex.getMessage());
        return GeneralResponse.error(HttpStatusConstant.UNAVAILABLE_CODE, HttpStatusConstant.UNAVAILABLE_MESSAGE);
    }
}
