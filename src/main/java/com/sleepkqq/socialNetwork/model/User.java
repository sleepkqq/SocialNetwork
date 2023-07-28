package com.sleepkqq.socialNetwork.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Role role;
    private boolean active;
    @OneToMany(mappedBy = "author")
    private List<Post> writtenPosts;
    @OneToMany(mappedBy = "userPage")
    private List<Post> postsOnPage;

}
