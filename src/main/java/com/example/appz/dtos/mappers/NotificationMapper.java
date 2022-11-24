package com.example.appz.dtos.mappers;

import com.example.appz.dtos.NotificationDTO;
import com.example.appz.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification mapNotificationDTO(NotificationDTO notificationDTO);

    NotificationDTO map(Notification notification);

    List<NotificationDTO> map(List<Notification> notificationList);
}