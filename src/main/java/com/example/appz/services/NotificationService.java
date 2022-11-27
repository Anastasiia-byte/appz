package com.example.appz.services;

import com.example.appz.dtos.MessageDTO;
import com.example.appz.dtos.NotificationDTO;
import com.example.appz.dtos.mappers.NotificationMapper;
import com.example.appz.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserService userService;

    public List<NotificationDTO> getAllForUser(long userId) {
        log.info("Retrieving all notifications for user " + userId);
        return notificationMapper.map(notificationRepository.getAllByUser_Id(userId));
    }

    @Transactional
    public void send(MessageDTO messageDTO, long chatId) {
        log.info("Sending a notification about a new message. Receiver id - " + messageDTO.getReceiverId());
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessageId(messageDTO.getId());
        notificationDTO.setUserId(messageDTO.getReceiverId());

        String userFullName = userService.getUserFullNameById(messageDTO.getSenderId());

        notificationDTO.setSenderFullName(userFullName);
        notificationDTO.setRead(false);

        notificationRepository.save(notificationMapper.mapNotificationDTO(notificationDTO));
        simpMessagingTemplate.convertAndSend("/queue/notification/" + messageDTO.getReceiverId(), notificationDTO);
    }

    public void delete(long id) {
        log.info("Notification " + id + " is read. Deleting");
        notificationRepository.deleteById(id);
    }

    public void deleteAllForUser(long userId) {
        log.info("Deleting all notification for user {}", userId);
        notificationRepository.deleteAllByUser_Id(userId);
    }
}
