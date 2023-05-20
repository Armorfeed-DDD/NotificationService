package com.armorfeed.api.notifications.domain.services;

import java.util.List;

import com.armorfeed.api.notifications.domain.entities.Notification;

public interface NotificationService {
    public List<Notification> getAllNotifications();
}
