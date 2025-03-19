package com.chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;

import com.chat.common.exception.CustomErrorException;
import com.chat.common.exception.ErrorCode;
import com.chat.domain.modeldto.ChatRoomDto;
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
	public ChatRoomDto createChatRoom(String chatRoomName, String userId) {
        String chatRoomId = UUID.randomUUID().toString();

        ChatRoom chatRoom = new ChatRoom(chatRoomId, chatRoomName);
        try {

//            ChatRoom res = chatRoomRepository.save(chatRoom);
//            Optional<ChatUser> user = chatUserRepository.findById(userId);
//
//            res.addUser(user.get());
        	
//        	Optional<ChatUser> user = chatUserRepository.findByUserIdAndUserStatus(userId, true );
        	ChatUser user = chatUserRepository.findByUserIdAndUserStatus(userId, true ).orElseThrow(() -> new CustomErrorException(ErrorCode.FAILURE));
        	
        	chatRoom.addUser(user);
            chatRoom.addMemberCnt();
            chatRoom.setUpdate();
        	ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        	ChatRoom res = Optional.of(savedRoom).orElseThrow( () -> new CustomErrorException(ErrorCode.FAILURE));
            return toDto(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ErrorResponse;
        return null;
	}

	@Override
	@Transactional
	public ChatRoomDto joinChatRoom(String roomId) {
        // ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomIdAndIsOpen(roomId, true).orElseThrow(() -> new CustomErrorException(ErrorCode.FAILURE));
        ChatUser joinUser = chatUserRepository.findByUserIdAndUserStatus("1", true).orElseThrow(() -> new CustomErrorException(ErrorCode.FAILURE));
        chatRoom.addUser(joinUser);
        chatRoom.addMemberCnt();
        chatRoom.setUpdate();
        
        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        ChatRoom room = Optional.of(savedRoom).orElseThrow( () -> new CustomErrorException(ErrorCode.FAILURE));
        return toDto(room);
	}

	@Override
	@Transactional
	public ChatRoomDto leaveRoom(String roomId) {
        // ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomIdAndIsOpen(roomId, true).orElseThrow(() -> new CustomErrorException(ErrorCode.FAILURE));
        ChatUser deleteUser = chatUserRepository.findByUserIdAndUserStatus("1", true).orElseThrow(() -> new CustomErrorException(ErrorCode.FAILURE));
        
        chatRoom.getChatUserList().remove(deleteUser);
        deleteUser.setChatRoom(null);
        chatRoom.subMemberCnt();
        chatRoom.setUpdate();
        
        if (chatRoom.getMemberCnt() > 0) {
            chatRoomRepository.save(chatRoom);
        } else {
            // chatRoomRepository.delete(chatRoom);
            chatRoom.isChatRoomOpen(false);
            chatRoomRepository.save(chatRoom);
        }

        return toDto(chatRoom);
	}

	@Override
	public List<ChatRoomDto> getRooms() {
        return chatRoomRepository.findAllByIsOpen(true).get().stream()
													        	.map(room -> toDto(room))
													        	.toList();
	}

	@Override
	public ChatRoomDto getRoom(String roomId) {
        // return chatRoomRepository.findByRoomId(roomId);
        return toDto(chatRoomRepository.findByRoomIdAndIsOpen(roomId, true).get());
	}

	@Override
	public void updateRoom(ChatRoom room) {
		room.setUpdate();
        chatRoomRepository.save(room);
	}
	
	private ChatRoomDto toDto(ChatRoom room) {
		return ChatRoomDto.builder()
				.roomId(room.getRoomId())
				.roomName(room.getRoomName())
				.updatedAt(room.getUpdatedAt().toString())
				.memberCnt(room.getMemberCnt())
				.isOpen(room.isOpen())
				.chatUserList(room.getChatUserList())
				.build();
	}

}
