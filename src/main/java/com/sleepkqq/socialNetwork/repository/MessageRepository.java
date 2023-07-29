package com.sleepkqq.socialNetwork.repository;

import com.sleepkqq.socialNetwork.model.Message;
import com.sleepkqq.socialNetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT u FROM User u " +
            "JOIN Message m ON u = m.author OR u = m.receiver " +
            "WHERE u <> :currentUser " +
            "GROUP BY u " +
            "ORDER BY MAX(m.date) DESC")
    List<User> findUsersWithMessagesForCurrentUser(@Param("currentUser") User currentUser);
    @Query("SELECT m FROM Message m " +
            "WHERE (m.author = :currentUser AND m.receiver = :receiver) " +
            "OR (m.author = :receiver AND m.receiver = :currentUser) " +
            "ORDER BY m.date ASC")
    List<Message> findMessagesBetweenUsersOrderByDate(@Param("currentUser") User currentUser,@Param("receiver") User receiver);
}
