package com.armorfeed.api.notifications.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.armorfeed.api.notifications.domain.entities.Notification;
import java.util.List;
import com.armorfeed.api.notifications.domain.enums.NotificationSender;



@Repository
@EnableJpaRepositories
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findBySenderAndCustomerId(NotificationSender sender, Long customerId);
    List<Notification> findBySenderAndEnterpriseId(NotificationSender sender, Long enterpriseId);
}
