package com.armorfeed.api.notifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armorfeed.api.notifications.domain.services.NotificationService;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;

@RestController
@RequestMapping("api/v1/notifications/")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/customers/{customerId}")
    public List<NotificationResponse> getAllNotificationsByCustomerId(@PathVariable("customerId") Long customerId) {
        return notificationService.getAllNotificationsByCustomerId(customerId);
    }
}
