package com.asif.Repository;

import com.asif.Entity.Chat;
import com.asif.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c where :user Member of c.users and :reqUser Member of c.users ")
    public Chat findChatByUsersId(@Param("user") User user,@Param("reqUser") User reqUser);
}
