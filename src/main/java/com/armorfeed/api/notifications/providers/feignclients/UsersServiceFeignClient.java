package com.armorfeed.api.notifications.providers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.armorfeed.api.notifications.providers.feignclients.dto.AuthTokenResponse;

@FeignClient(value = "users-service", url = "http://localhost:8090")
public interface UsersServiceFeignClient {
    @GetMapping("/api/v1/users/auth/validate-token/{token}")
    public AuthTokenResponse validateToken(@PathVariable("token") String token);

    @GetMapping("/api/v1/users/auth/validate-enterprise/{enterpriseId}")
    public boolean validateEnterpriseId(@PathVariable("enterpriseId") Long enterpriseId);

    @GetMapping("/api/v1/users/auth/validate-customer/{customerId}")
    public boolean validateCustomerId(@PathVariable("customerId") Long customerId);
}
