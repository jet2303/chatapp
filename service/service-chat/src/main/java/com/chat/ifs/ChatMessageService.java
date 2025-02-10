package com.chat.ifs;

import com.chat.model.ChatMessage;

public interface ChatMessageService {
    
    public void join(ChatMessage message);

    public void sndMessage(ChatMessage message);

//    public void leave(ChatMessage message, String userId);
    public void leave(ChatMessage message);
}
