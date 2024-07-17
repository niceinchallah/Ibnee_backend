package com.test.test.service.impl;

import com.test.test.DTO.NotificationDTO;
import com.test.test.entity.Notification;
import com.test.test.repository.NotificationRepository;
import com.test.test.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<NotificationDTO> getRecentNotifications() {
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        List<Notification> notifications = notificationRepository.findByCreatedAtAfter(last24Hours);
        return notifications.stream()
                .map(notification -> new NotificationDTO(notification.getType(), notification.getMessage(), notification.getRelatedId()))
                .collect(Collectors.toList());
    }
}
