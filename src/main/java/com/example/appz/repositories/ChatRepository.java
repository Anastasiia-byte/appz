package com.example.appz.repositories;

import com.example.appz.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "select * from chats c join messages m on c.id = m.chat where m.receiver = :userId", nativeQuery = true)
    List<Chat> getAllByUser(long userId);

}
