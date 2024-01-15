package com.asif.Controllers;

import com.asif.Entity.Reels;
import com.asif.Entity.User;
import com.asif.Repository.ReelsRepository;
import com.asif.Service.ReelsServiceImpl;
import com.asif.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsServiceImpl reelsService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels,
                              @RequestHeader("Authorization") String jwt) {
        User RequestedUser = userService.findUserByJwt(jwt);
        return reelsService.createReels(reels, RequestedUser);
    }

    @GetMapping("/api/reels")
    public List<Reels> createReels() {
        return reelsService.findAllReels();
    }
    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        return reelsService.findUsersReels(userId);
    }
}
