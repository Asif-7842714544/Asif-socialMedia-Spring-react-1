package com.asif.Controllers;

import com.asif.Entity.User;
import com.asif.Repository.UserRepository;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/api/allUsers")
    public List<User> getAllUsers() {
        return userService.getallUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PutMapping("/api/updateUser")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        //the User who is login can update his own profile not others
        User reqUser = getUserFromToken(jwt);
        return userService.updateUser(user, reqUser.getId());
    }

    @PutMapping("/api/followUser/{userId1}/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
      User requestedUser= getUserFromToken(jwt);
        return userService.followUser(requestedUser.getId(), userId2);
    }

    @GetMapping("/searchUsers")
    public List<User> searchUserHandler(@RequestParam("query") String query) throws Exception {
        return userService.searchUser(query);
    }

//    @DeleteMapping("/deleteUserById/{id}")
//    public String deleteUserById(@PathVariable Integer id) throws Exception {
//        return userRepository.findById(id)
//                .map(user -> {
//                    userRepository.delete(user);
//                    return "User deleted successfully";
//                })
//                .orElseThrow(() -> new Exception("User not exist with userid " + id));
//    }


    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        System.out.println("Jwt --" + jwt);
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
