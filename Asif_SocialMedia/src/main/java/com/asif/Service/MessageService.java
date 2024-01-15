package com.asif.Service;

import com.asif.Entity.Chat;
import com.asif.Entity.Message;
import com.asif.Entity.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
