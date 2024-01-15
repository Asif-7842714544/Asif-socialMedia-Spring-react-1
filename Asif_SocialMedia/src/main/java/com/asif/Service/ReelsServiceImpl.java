package com.asif.Service;

import com.asif.Entity.Reels;
import com.asif.Entity.User;
import com.asif.Repository.ReelsRepository;
import com.asif.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Reels createReels(Reels reels, User user) {
        Reels createdReel = new Reels();
        createdReel.setTitle(reels.getTitle());
        createdReel.setUser(user);
        createdReel.setVideoUrl(reels.getVideoUrl());

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(int userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
