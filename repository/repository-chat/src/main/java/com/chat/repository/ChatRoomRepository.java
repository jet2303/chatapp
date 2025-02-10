package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{
    ChatRoom findByRoomId(String chatId);

    ChatRoom findByRoomIdAndIsOpen(String chatId, boolean roomStatus);

    List<ChatRoom> findAllByIsOpen(boolean roomStatus);
}
