package com.asif.Controllers;

import com.asif.Entity.Post;
import com.asif.Entity.User;
import com.asif.Response.ApiResponse;
import com.asif.Service.PostServiceImpl;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/createPost")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,@RequestBody Post post) throws Exception {
        User requestedUser=userService.findUserByJwt(jwt);
        Post createdPost = postService.createPost(post, requestedUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
        User requestedUser=userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, requestedUser.getId());
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findAllUsersPost(@PathVariable Integer userId) throws Exception {
        List<Post> postsByUserId = postService.findPostsByUserId(userId);
        return new ResponseEntity<>(postsByUserId, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() throws Exception {
        List<Post> allPost = postService.findAllPost();
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
        User requestedUser=userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, requestedUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
        User requestedUser=userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, requestedUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
