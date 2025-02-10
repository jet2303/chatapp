package com.chat.ifs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chat.CommonResponse;
import com.chat.model.ChatRoom;

//import jakarta.transaction.Transactional;

public interface ChatRoomService {
    
    public ChatRoom createChatRoom(String chatRoomName, String userId);

    public CommonResponse<ChatRoom> joinChatRoom(String roomId);
    
    public ChatRoom leaveRoom(String roomId);

    public List<ChatRoom> getRooms();

    public ChatRoom getRoom(String roomId);

    public void updateRoom(ChatRoom room);
}
