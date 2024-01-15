package com.asif.Repository;

import com.asif.Entity.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {

    public List<Reels> findByUserId(int userId);

}
