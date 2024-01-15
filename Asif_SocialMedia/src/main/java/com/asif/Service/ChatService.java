package com.asif.Service;

import com.asif.Entity.Chat;
import com.asif.Entity.User;

import java.util.List;

public interface ChatService {

    public Chat creatChat(User reqUser,User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChats(Integer UserId);
}
