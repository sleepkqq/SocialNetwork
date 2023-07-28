package com.sleepkqq.socialNetwork.repository;

import com.sleepkqq.socialNetwork.model.Post;
import com.sleepkqq.socialNetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserPage(User userPage);
    List<Post> findPostsByAuthor(User author);
}