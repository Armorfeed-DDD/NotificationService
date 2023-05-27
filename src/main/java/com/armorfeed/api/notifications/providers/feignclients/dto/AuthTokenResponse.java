package com.armorfeed.api.notifications.providers.feignclients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenResponse {
    private boolean isValidToken;
    private String message;
}
