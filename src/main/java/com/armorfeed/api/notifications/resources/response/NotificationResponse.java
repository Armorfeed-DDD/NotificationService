package com.armorfeed.api.notifications.resources.response;

import com.armorfeed.api.notifications.domain.enums.NotificationSender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String title;
    private String message;
    private NotificationSender sender;
    private Long customerId;
    private Long enterpriseId;
}
