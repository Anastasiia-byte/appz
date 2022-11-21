package com.example.appz.controllers;

import com.example.appz.dtos.NotificationDTO;
import com.example.appz.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notification")
@Validated
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public List<NotificationDTO> getAllForUser(@PathVariable @Min(0) long userId) {
        log.info("Received request to get all notifications for user " + userId);
        return notificationService.getAllForUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(0) long id) {
        log.info("Received request to delete notification " + id);
        notificationService.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteAllForUser(@PathVariable @Min(0) long userId) {
        log.info("Received request to delete all notifications for user " + userId);
        notificationService.deleteAllForUser(userId);
    }

}
