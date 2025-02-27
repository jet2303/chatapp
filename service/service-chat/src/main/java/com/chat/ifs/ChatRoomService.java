package com.chat.ifs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chat.CommonResponse;
import com.chat.model.ChatRoom;
import com.chat.modeldto.ChatRoomDto;

//import jakarta.transaction.Transactional;

public interface ChatRoomService {
    
    public ChatRoomDto createChatRoom(String chatRoomName, String userId);

    public ChatRoomDto joinChatRoom(String roomId);
    
    public ChatRoomDto leaveRoom(String roomId);

    public List<ChatRoomDto> getRooms();

    public ChatRoomDto getRoom(String roomId);

    public void updateRoom(ChatRoom room);
}
