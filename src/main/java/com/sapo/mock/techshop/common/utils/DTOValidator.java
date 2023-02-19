package com.sapo.mock.techshop.common.utils;

import com.sapo.mock.techshop.common.exception.BusinessException;
import com.sapo.mock.techshop.common.constant.HttpStatusConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Component
public class DTOValidator {
    @Autowired
    private Validator validator;

    public void validate(Object dto) {
        List<String> errors = new ArrayList<>();
        validator.validate(dto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException(HttpStatusConstant.INVALID_DATA_CODE, errors.get(0));
        }
    }
}
