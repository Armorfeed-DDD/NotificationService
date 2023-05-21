package com.armorfeed.api.notifications.domain.enums;

public enum NotificationSender {
    CUSTOMER("Customer"),
    ENTERPRISE("Enterprise");
    private String value;
    private NotificationSender(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
