package com.example.appz.dtos.mappers;

import com.example.appz.dtos.NotificationDTO;
import com.example.appz.entities.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification mapNotificationDTO(NotificationDTO notificationDTO);

    NotificationDTO map(Notification notification);

    List<NotificationDTO> map(List<Notification> notificationList);
}
