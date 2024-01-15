package com.asif.Controllers;

import com.asif.Entity.Chat;
import com.asif.Entity.User;
import com.asif.Request.CreateChatRequest;
import com.asif.Service.ChatServiceImpl;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/chats")
    private Chat createChat(@RequestHeader("Authorization") String jwt,
                            @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        return chatService.creatChat(reqUser, user2);
    }

    @GetMapping("/api/chats")
    private List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return chatService.findUsersChats(user.getId());
    }

}
