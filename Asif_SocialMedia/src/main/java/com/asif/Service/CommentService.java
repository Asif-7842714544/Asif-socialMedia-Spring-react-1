package com.asif.Service;

import com.asif.Entity.Comment;

public interface CommentService {

    public Comment creatComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId);

    public Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
