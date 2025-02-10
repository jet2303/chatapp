package com.chat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.ChatRoom;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/chatRooms")
public class ChatRoomController {
	private final ChatRoomServiceImpl roomService;

    public ChatRoomController(ChatRoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    /***
     * 채팅방 목록 페이지 VIEW
     * 
     * @param request
     * @return
     */
    @GetMapping("")
    public String chatRooms(HttpServletRequest request) {
        return "content/chatRooms";
    }

    /***
     * 채팅방 목록 조회
     * 
     * @param request
     * @return
     */
    @GetMapping("/getRooms")
    public @ResponseBody List<ChatRoom> getRooms(HttpServletRequest request) {
        return roomService.getRooms();
    }

    /***
     * 채팅방 생성
     * 
     * @param request
     * @return
     */
    // public @ResponseBody ResponseEntity<? extends BasicResponse>
    // createRoom(HttpServletRequest request, @RequestBody Map<String, String>
    // roomName) {
    @PostMapping(value = "/createRoom")
    public ResponseEntity<? extends CommonResponse<ChatRoom>> createRoom(HttpServletRequest request,
            @RequestBody Map<String, String> roomName) {
        Cookie[] cookies = request.getCookies();
        List<Cookie> cookieList = Arrays.asList(cookies);
        Cookie resCookie = cookieList.stream().filter(cookie -> cookie.getName().equals("userId"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("맞는 쿠키X"));
        String userId = resCookie.getValue();

        ChatRoom room = roomService.createChatRoom(roomName.get("roomName"), userId);
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse<>(room));
    }

    /***
     * 채팅방 페이지 VIEW
     * 
     * @param request
     * @return
     */
    @GetMapping("/room/{roomId}")
    public ModelAndView chatRoom(HttpServletRequest request, @PathVariable String roomId, ModelAndView mv) {
        // ChatRoom room = roomService.joinChatRoom(roomId);
        CommonResponse<ChatRoom> room = roomService.joinChatRoom(roomId);
        mv.addObject("room", room.getData());
        // dto로 받아올것.
        // mv.addObject("participants", room.getData().getChatUserList());
        mv.setViewName("content/chatRoom");
        return mv;
    }
}
