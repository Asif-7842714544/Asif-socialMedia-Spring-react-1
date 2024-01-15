package com.asif.Service;

import com.asif.Entity.Reels;
import com.asif.Entity.User;

import java.util.List;

public interface ReelsService {

    public Reels createReels(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(int userId) throws Exception;

}
