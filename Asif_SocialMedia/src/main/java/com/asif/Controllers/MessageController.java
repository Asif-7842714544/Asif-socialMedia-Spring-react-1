package com.asif.Controllers;

import com.asif.Entity.Message;
import com.asif.Entity.User;
import com.asif.Service.MessageServiceImpl;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/messages/chat/{chatId}")
    private Message createMessage(@RequestBody Message req,
                                  @RequestHeader("Authorization") String jwt,
                                  @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        return messageService.createMessage(user, chatId, req);

    }

    @GetMapping("/api/messages/chat/{chatId}")
    private List<Message> findChatMessages(@RequestHeader("Authorization") String jwt,
                                           @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        return messageService.findChatsMessages(chatId);

    }


}
