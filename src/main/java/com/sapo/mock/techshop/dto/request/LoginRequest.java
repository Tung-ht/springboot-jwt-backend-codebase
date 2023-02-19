package com.sapo.mock.techshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "username is required!")
    private String username;
    @NotBlank(message = "password is required!")
    private String password;

    /*
     * role:
     *  1: manager
     *  2: admin
     *  3: user
     */
    @NotBlank(message = "role is required!")
    private Integer role;
}
