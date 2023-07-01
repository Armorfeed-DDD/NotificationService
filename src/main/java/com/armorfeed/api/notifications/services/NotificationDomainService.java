package com.armorfeed.api.notifications.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.armorfeed.api.notifications.domain.entities.Notification;
import com.armorfeed.api.notifications.domain.enums.NotificationSender;
import com.armorfeed.api.notifications.domain.services.NotificationService;
import com.armorfeed.api.notifications.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.notifications.repositories.NotificationRepository;
import com.armorfeed.api.notifications.resources.response.CreateNotificationRequest;
import com.armorfeed.api.notifications.resources.response.NotificationResponse;
import com.armorfeed.api.notifications.shared.mapping.EnhancedModelMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NotificationDomainService implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    @Autowired
    UsersServiceFeignClient usersServiceFeignClient;

    @Override
    public List<NotificationResponse> getAllNotificationsByEnterpriseId(Long enterpriseId) {
        List<Notification> notifications = notificationRepository.findBySenderAndEnterpriseId(NotificationSender.CUSTOMER, enterpriseId);
        List<NotificationResponse> result = enhancedModelMapper.mapList(notifications,NotificationResponse.class);
        return result;
    }

    @Override
    public List<NotificationResponse> getAllNotificationsByCustomerId(Long customerId) {
        List<Notification> notifications = notificationRepository.findBySenderAndCustomerId(NotificationSender.ENTERPRISE, customerId);
        List<NotificationResponse> result = enhancedModelMapper.mapList(notifications,NotificationResponse.class);
        return result;
    }
    @Override
    public ResponseEntity<?> createNotification(CreateNotificationRequest request){
        boolean validCustomerId = usersServiceFeignClient.validateCustomerId(request.getCustomerId());
        boolean validEnterpriseId = usersServiceFeignClient.validateEnterpriseId(request.getEnterpriseId());
        List<String> errors = new LinkedList<>();
        
        if(validCustomerId == false) {
            log.info("Customer Id {} does not exist", request.getCustomerId());
            errors.add(String.format("Customer Id %d does not exist", request.getCustomerId()));
        }

        if(validEnterpriseId == false) {
            log.info("Enteprise Id {} does not exist", request.getEnterpriseId());
            errors.add(String.format("Enterprise Id %d does not exist", request.getEnterpriseId()));
        }

        if(errors.isEmpty() == false) {
            return ResponseEntity.badRequest().body(errors);
        }

        try{
            Notification newNotification = new Notification(0L, request.getTitle(), request.getMessage(),NotificationSender.valueOf(request.getSender()), request.getCustomerId(), request.getEnterpriseId());
            log.info("New notification to insert is {}", newNotification);
            Notification result = notificationRepository.save(newNotification);
            return ResponseEntity.ok().body(result);

        }catch (Exception e){
            String messageError="an error has occurred while saving the data: "+e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }

    }
}
