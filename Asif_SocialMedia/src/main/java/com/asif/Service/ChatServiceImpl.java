package com.asif.Service;

import com.asif.Entity.Chat;
import com.asif.Entity.User;
import com.asif.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Chat creatChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);
        if (isExist != null) {
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        return chatRepository.findById(chatId).orElseThrow(()->new Exception("Chat not found with id: " + chatId));

    }

    @Override
    public List<Chat> findUsersChats(Integer UserId) {
        return chatRepository.findByUsersId(UserId);

    }
}
