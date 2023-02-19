package com.sapo.mock.techshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserInfoRequest {
    private String username;
    private String password;
    private String role;
}
