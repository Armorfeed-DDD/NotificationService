package com.armorfeed.api.notifications.providers.feignclients;

import lombok.Getter;

@Getter
public class AuthTokenResponse {
    private boolean isValidToken;
    private String message;
}
