package com.example.appz.dtos;

import lombok.Data;

@Data
public class NotificationDTO {
    private long id;
    private long userId;
    private long messageId;
    private boolean read;
    private String senderFullName;
}
