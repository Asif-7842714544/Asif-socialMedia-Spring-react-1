package com.asif.Service;

import com.asif.Entity.Comment;
import com.asif.Entity.Post;
import com.asif.Entity.User;
import com.asif.Repository.CommentRepository;
import com.asif.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment creatComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.getPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedDate(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment commentById = findCommentById(commentId);
        User user = userService.findUserById(userId);
        if (!commentById.getLiked().contains(user)) {
            commentById.getLiked().add(user);
        } else {
            commentById.getLiked().remove(user);
        }
        return commentRepository.save(commentById);

    }
}
