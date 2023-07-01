package com.armorfeed.api.notifications.domain.services;

import java.util.List;

import com.armorfeed.api.notifications.resources.response.CreateNotificationRequest;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    public List<NotificationResponse> getAllNotificationsByEnterpriseId(Long enterpriseId);
    public List<NotificationResponse> getAllNotificationsByCustomerId(Long customerId);

    public ResponseEntity<?> createNotification(CreateNotificationRequest request);
}
