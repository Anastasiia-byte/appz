package com.example.appz.repositories;

import com.example.appz.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getAllByUser_Id(long id);

    void deleteAllByUser_Id(long id);
}
