package com.asif.Controllers;

import com.asif.Entity.Comment;
import com.asif.Entity.User;
import com.asif.Service.CommentServiceImpl;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Commentcontroller {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment creatComment(@RequestBody Comment comment,
                                @RequestHeader("Authorization") String jwt,
                                @PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return commentService.creatComment(comment, postId, user.getId());
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                               @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return commentService.likeComment(commentId, user.getId());
    }
}
