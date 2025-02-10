package com.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser, String> {

    ChatUser findByUserId(String userId);

    Optional<ChatUser> findByUserIdAndUserStatus(String userId, boolean userStatus);

    Optional<ChatUser> findByUserNameAndUserStatus(String userName, boolean userStatus);

    boolean existsByUserId(String userId);
}
