package com.example.appz.repositories;

import com.example.appz.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "select distinct c.id from chats c inner join chats_messages cm on c.id = cm.chat_id inner join messages m on cm.messages_id = m.id where m.receiver_id = :userId or m.sender_id = :userId", nativeQuery = true)
    List<Chat> getAllByUser(long userId);

}
