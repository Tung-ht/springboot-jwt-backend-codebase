package com.sapo.mock.techshop.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserInfo {
    private String username;
    private String role;
    private String accessToken;
    private String refreshToken;
}
