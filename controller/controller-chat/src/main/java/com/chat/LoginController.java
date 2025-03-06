package com.chat;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat.event.UserRegEvent;
import com.chat.ifs.ChatUserService;
import com.chat.model.ChatUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	private final ApplicationEventPublisher applicationEventPublisher;

//	private final ChatUserService chatUserService;
	
//    public LoginController(ChatUserService userService, ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//        this.chatUserService = userService;
//    }

    /***
     * 로그인 페이지
     * 
     * @param request
     */
    @GetMapping("")
    public String login(HttpServletRequest request) {
        return "content/login";
    }

    /***
     * 로그인 처리
     * 
     * @param response
     * @param user
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<? extends CommonResponse> user(HttpServletRequest request, HttpServletResponse response,
            @RequestBody ChatUser user) {
        // userId 생성
        user.setUserid(String.valueOf(UUID.randomUUID()));
        log.info("login user Name :  " + user.getUserName());
        log.info("user Id : " + user.getUserId());

        // UserRegEvent userRegEvent = new UserRegEvent(user, request, response);
        applicationEventPublisher.publishEvent(new UserRegEvent(user, request, response));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
