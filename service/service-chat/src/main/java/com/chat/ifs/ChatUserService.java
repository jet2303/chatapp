package com.chat.ifs;

import java.util.List;

import com.chat.model.ChatUser;

public interface ChatUserService {
    public List<ChatUser> getUsers();
    public ChatUser getUser(String userId);
    public void updateUser(ChatUser user);    
    public void saveUser(ChatUser user);
}
