package com.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{
    Optional<ChatRoom> findByRoomId(String chatId);

    Optional<ChatRoom> findByRoomIdAndIsOpen(String chatId, boolean roomStatus);

    Optional<List<ChatRoom>> findAllByIsOpen(boolean roomStatus);
}
