package com.armorfeed.api.notifications.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.armorfeed.api.notifications.domain.enums.NotificationSender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "notifications")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private NotificationSender sender;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long enterpriseId;
}
