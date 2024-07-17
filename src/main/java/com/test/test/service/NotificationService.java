package com.test.test.service;

import com.test.test.DTO.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getRecentNotifications();
}
