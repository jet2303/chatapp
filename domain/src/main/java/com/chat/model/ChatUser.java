package com.chat.model;

import java.time.LocalDateTime;

import com.chat.common.custom.customConst.UserNameConst;
import com.chat.model.userrole.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ChatUser extends BaseEntity {
	@Id
	@Column(name =  "user_id")
    private String userId;
	
	@Column(name = "user_name")
//	@UserNameConst
    private String userName;
	
	@Column(name = "user_status")
    private boolean userStatus;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    
//	@Column(name = "created_at")
//    private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private ChatRoom chatRoom;

    public ChatUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.userStatus = true;
    }

    public ChatUser(String userId, String userName, String password, UserRole role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public void setRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        if (!chatRoom.getChatUserList().contains(this)) {
            chatRoom.getChatUserList().add(this);
        }
    }
}
