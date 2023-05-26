package com.armorfeed.api.notifications.services;

import java.util.List;
import java.util.Optional;

import com.armorfeed.api.notifications.resources.response.CreateNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.armorfeed.api.notifications.domain.entities.Notification;
import com.armorfeed.api.notifications.domain.enums.NotificationSender;
import com.armorfeed.api.notifications.domain.services.NotificationService;
import com.armorfeed.api.notifications.repositories.NotificationRepository;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;
import com.armorfeed.api.notifications.shared.mapping.EnhancedModelMapper;

@Service
public class NotificationDomainService implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    @Override
    public List<Notification> getAllNotifications() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotifications'");
    }

    @Override
    public List<NotificationResponse> getAllNotificationsByCustomerId(Long customerId) {
        List<Notification> notifications = notificationRepository.findBySenderAndCustomerId(NotificationSender.ENTERPRISE, customerId);
        List<NotificationResponse> result = enhancedModelMapper.mapList(notifications,NotificationResponse.class);
        return result;
    }
    @Override
    public ResponseEntity<String> createNotification(CreateNotificationRequest request){

        try{
            Notification newNotification = new Notification(0L, request.getTitle(), request.getMessage(),NotificationSender.valueOf(request.getSender()), request.getCustomerId(), request.getEnterpriseId());
            notificationRepository.save(newNotification);
            return ResponseEntity.ok("Notification created!!!");

        }catch (Exception e){
            String messageError="an error has occurred while saving the data: "+e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }

    }
}
