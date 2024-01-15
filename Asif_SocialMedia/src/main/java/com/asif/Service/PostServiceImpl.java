package com.asif.Service;

import com.asif.Entity.Post;
import com.asif.Entity.User;
import com.asif.Repository.PostRepository;
import com.asif.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userServiceimpl;

    @Autowired
    UserRepository  userRepository;

    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userServiceimpl.findUserById(userId));
        return postRepository.save(newPost);

    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = getPostById(postId);
        User user = userServiceimpl.findUserById(userId);
        if (post.getUser().getId() != user.getId()) {
            throw new Exception("You are not authorized to delete this post");
        } else {
            postRepository.delete(post);
            return "Post deleted successfully";
        }

    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) throws Exception {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post getPostById(Integer postId) throws Exception {
        return postRepository.findById(postId).orElseThrow(() -> new Exception("Post Not Found"));
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = getPostById(postId);
        User user = userServiceimpl.findUserById(userId);
        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }else{
            user.getSavedPosts().add(post);
        }
        userRepository.save(user);
        return postRepository.save(post);
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = getPostById(postId);
        User user = userServiceimpl.findUserById(userId);
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
