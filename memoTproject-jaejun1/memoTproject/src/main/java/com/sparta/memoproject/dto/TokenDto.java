package com.sparta.memoproject.dto;

import lombok.*;
import org.springframework.security.web.header.Header;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
