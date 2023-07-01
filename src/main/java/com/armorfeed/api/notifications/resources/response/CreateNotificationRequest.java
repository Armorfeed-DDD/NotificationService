package com.armorfeed.api.notifications.resources.response;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.armorfeed.api.notifications.domain.enums.NotificationSender;
import com.armorfeed.api.notifications.utils.ValidateCustomEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {
    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String message;

    @ValidateCustomEnum(
        enumClass = NotificationSender.class,
        message = "Sender must be one of these values [CUSTOMER, ENTERPRISE]"
    )
    private String sender;

    @NotNull
    private Long customerId;

    @NotNull
    private Long enterpriseId;
}
