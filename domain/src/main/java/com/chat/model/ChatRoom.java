package com.chat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class ChatRoom extends BaseEntity{

	@Id
	@Column(name = "room_id")
	private String roomId;
	
	@Column(name = "room_name")
    private String roomName;

    // BaseEntity 추가로 인한 삭제
    // private LocalDateTime createdAt;
	@Column(name = "updated_at")
    private LocalDateTime updatedAt;

	@Column(name = "member_cnt")
    private int memberCnt = 0;

	@Column(name = "is_open")
    private boolean isOpen = true;
    
//    @Builder.Default
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatUser> chatUserList = new ArrayList<>();
    
    public ChatRoom(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.isOpen = true;
        // this.createdAt = LocalDateTime.now();
        // this.updatedAt = LocalDateTime.now();
        this.memberCnt = 0;
    }

    public void addMemberCnt() {
        this.memberCnt++;
    }

    public void subMemberCnt() {
        if (this.memberCnt <= 0) {
            this.memberCnt = 0;
        } else {
            this.memberCnt--;
        }

    }

    public void setUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void isChatRoomOpen(boolean status) {
        this.isOpen = status;
    }

    public void addUser(ChatUser user) {
        this.chatUserList.add(user);
        user.setRoom(this);
    }
}
