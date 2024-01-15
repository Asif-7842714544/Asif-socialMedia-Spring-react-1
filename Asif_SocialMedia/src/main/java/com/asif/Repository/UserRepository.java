package com.asif.Repository;

import com.asif.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    @Query("select u from User u where u.firstName like %:query% or u.lastName like %:query% or u.email like %:query%")
    public List<User> searchUser(@Param("query") String query);

//    @Query("select u from User u where u.firstName like %?1 or u.lastName like %?1 or u.email like %?1")
//    public List<User> searchUser(String query);
}
