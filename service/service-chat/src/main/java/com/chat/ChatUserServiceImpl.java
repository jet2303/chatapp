package com.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chat.model.ChatUser;
import com.chat.repository.ChatUserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatUserServiceImpl implements com.chat.ifs.ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public void setSessionAndCookie(HttpServletRequest request, HttpServletResponse response, ChatUser chatUser) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", chatUser.getUserId());
        session.setMaxInactiveInterval(60 * 10);

        response.addCookie(createCookie("userId", chatUser.getUserId(), request));
        response.addCookie(createCookie("userName", chatUser.getUserName(), request));
    }
    
    private Cookie createCookie(String name, String value, HttpServletRequest request) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(request.getRequestURI().replace(request.getRequestURI(), ""));
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        return cookie;
    }
	
	@Override
	public List<ChatUser> getUsers() {
        return chatUserRepository.findAll();
	}

	@Override
	public ChatUser getUser(String userId) {
        // return chatUserRepository.findByUserId(userId);
        return chatUserRepository.findByUserIdAndUserStatus(userId, true).get();
	}

	@Override
	public void updateUser(ChatUser user) {
	    chatUserRepository.save(user);
	}

	@Override
	public void saveUser(ChatUser user) {
        chatUserRepository.save(user);
	}

}
