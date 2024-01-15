package com.asif.Service;

import com.asif.Entity.Post;

import java.util.List;

public interface PostService {

    public Post createPost(Post post,Integer userId) throws Exception;

    public String deletePost(Integer postId,Integer userId) throws Exception;

    public List<Post> findPostsByUserId(Integer userId) throws Exception;

    public Post getPostById(Integer postId) throws Exception;

    public List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws Exception;

    Post likePost(Integer postId,Integer userId) throws Exception;

}
