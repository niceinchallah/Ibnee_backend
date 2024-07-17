package com.test.test.DTO;

import java.time.LocalDateTime;

public class NotificationDTO {
    private String type;
    private String message;
    private LocalDateTime createdAt;
    private Long relatedId;

    // Constructors
    public NotificationDTO() {}

    public NotificationDTO(String type, String message, Long relatedId) {
        this.type = type;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.relatedId = relatedId;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }
}
