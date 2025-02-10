package com.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.model.userrole.UserRole;
import com.chat.repository.ChatRoomRepository;
import com.chat.repository.ChatUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChatRoomServiceImpl implements com.chat.ifs.ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;
	
	@Override
	@Transactional
	public ChatRoom createChatRoom(String chatRoomName, String userId) {
        String chatRoomId = UUID.randomUUID().toString();

        ChatRoom chatRoom = new ChatRoom(chatRoomId, chatRoomName);
        try {

//            ChatRoom res = chatRoomRepository.save(chatRoom);
//            Optional<ChatUser> user = chatUserRepository.findById(userId);
//
//            res.addUser(user.get());
        	
        	Optional<ChatUser> user = chatUserRepository.findByUserIdAndUserStatus(userId, true );
        	chatRoom.addUser(user.get());
            chatRoom.addMemberCnt();
            chatRoom.setUpdate();
        	ChatRoom res = chatRoomRepository.save(chatRoom);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ErrorResponse;
        return null;
	}

	@Override
	@Transactional
	public CommonResponse<ChatRoom> joinChatRoom(String roomId) {
        // ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomIdAndIsOpen(roomId, true);
        Optional<ChatUser> joinUser = chatUserRepository.findByUserIdAndUserStatus("1", true);
        chatRoom.addUser(joinUser.get());
        chatRoom.addMemberCnt();
        chatRoom.setUpdate();
        chatRoomRepository.save(chatRoom);
        return new CommonResponse<ChatRoom>(chatRoom);
	}

	@Override
	@Transactional
	public ChatRoom leaveRoom(String roomId) {
        // ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomIdAndIsOpen(roomId, true);
        Optional<ChatUser> deleteUser = chatUserRepository.findByUserIdAndUserStatus("1", true);
        
        chatRoom.getChatUserList().remove(deleteUser.get());
        deleteUser.get().setChatRoom(null);
        chatRoom.subMemberCnt();
        chatRoom.setUpdate();
        
        if (chatRoom.getMemberCnt() > 0) {
            chatRoomRepository.save(chatRoom);
        } else {
            // chatRoomRepository.delete(chatRoom);
            chatRoom.isChatRoomOpen(false);
            chatRoomRepository.save(chatRoom);
            // log.info("{}", chatRoomRepository.findByRoomId(roomId));
            // log.info("{}", chatRoomRepository.findByRoomIdAndIsOpen(roomId, true));
        }

        return chatRoom;
	}

	@Override
	public List<ChatRoom> getRooms() {
		// return chatRoomRepository.findAll();
        return chatRoomRepository.findAllByIsOpen(true);
	}

	@Override
	public ChatRoom getRoom(String roomId) {
        // return chatRoomRepository.findByRoomId(roomId);
        return chatRoomRepository.findByRoomIdAndIsOpen(roomId, true);
	}

	@Override
	public void updateRoom(ChatRoom room) {
		room.setUpdate();
        chatRoomRepository.save(room);
	}

}
