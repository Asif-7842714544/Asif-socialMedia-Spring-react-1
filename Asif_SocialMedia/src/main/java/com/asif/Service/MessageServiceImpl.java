package com.asif.Service;

import com.asif.Entity.Chat;
import com.asif.Entity.Message;
import com.asif.Entity.User;
import com.asif.Repository.ChatRepository;
import com.asif.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatServiceImpl chatService;

    @Autowired
    ChatRepository chatRepository;


    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Message message = new Message();

        Chat chat = chatService.findChatById(chatId);

        message.setChat(chat);
        message.setMessage(req.getMessage());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimeStamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
    }
}
