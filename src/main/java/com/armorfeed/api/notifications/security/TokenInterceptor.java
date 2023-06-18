package com.armorfeed.api.notifications.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.armorfeed.api.notifications.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.notifications.providers.feignclients.dto.AuthTokenResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersServiceFeignClient usersServiceFeignClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getRequestURI().startsWith("/swagger-ui/") || request.getRequestURI().startsWith("/api-docs")) {
            return true;
        }
        if (!isValidToken(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }    
        return true;
    }

    private enum AuthTokenMessage {
        OK("Sucessfull authentication");
        private String message;
        private AuthTokenMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }
    }

    private boolean isValidToken(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if(uri.equals("/swagger-ui/index.html")) {
            return true;
        }
        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header is {}", authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            log.info("The token is {}", token);
            AuthTokenResponse authTokenResponse = usersServiceFeignClient.validateToken(token);
            log.info("Is valid token is {} and message is {}", authTokenResponse.isValidToken(), authTokenResponse.getMessage());
            return authTokenResponse.isValidToken();
        }
        return false;
    }
    
}
