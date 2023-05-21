package com.armorfeed.api.notifications.domain.services;

import java.util.List;

import com.armorfeed.api.notifications.domain.entities.Notification;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;

public interface NotificationService {
    public List<Notification> getAllNotifications();
    public List<NotificationResponse> getAllNotificationsByCustomerId(Long customerId);
}
