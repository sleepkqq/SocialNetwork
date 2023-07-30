package com.sleepkqq.socialNetwork.service;

import com.sleepkqq.socialNetwork.model.Post;
import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void addPost(String text, User author, User userPage) {
        Post post = new Post();
        post.setText(text);
        post.setUserPage(userPage);
        post.setAuthor(author);
        post.setDate(new Date());
        postRepository.save(post);
        log.info("Saved post on user page: {}, by: {}", userPage.getUsername(), author.getUsername());
    }

}
