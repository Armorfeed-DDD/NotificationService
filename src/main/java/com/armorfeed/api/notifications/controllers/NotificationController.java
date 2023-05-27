package com.armorfeed.api.notifications.controllers;

import java.util.List;

import com.armorfeed.api.notifications.domain.entities.Notification;
import com.armorfeed.api.notifications.resources.response.CreateNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/enterprises/{enterpriseId}")
    public List<NotificationResponse> getAllNotificationsByEnterpriseId(@PathVariable("enterpriseId") Long enterpriseId) {
        return notificationService.getAllNotificationsByEnterpriseId(enterpriseId);
    }

    @PostMapping("/create-notification")
    public ResponseEntity<String> createNotification(@RequestBody CreateNotificationRequest request){
        return notificationService.createNotification(request);
    }
}
