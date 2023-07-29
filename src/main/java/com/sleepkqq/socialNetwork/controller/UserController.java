package com.sleepkqq.socialNetwork.controller;

import com.sleepkqq.socialNetwork.model.Post;
import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.service.PostService;
import com.sleepkqq.socialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping("/user/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        if (!userService.register(user)) {
            model.addAttribute("error", "Registration Error");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String profile(Model model, @PathVariable Long id, Authentication authentication) {
        User user = userService.getUser(id);
        boolean find = user != null;
        model.addAttribute("find", find);
        if (find) {
            List<Post> posts = user.getPostsOnPage();
            Collections.reverse(posts);
            model.addAttribute("user", user);
            model.addAttribute("posts", posts);
            model.addAttribute("currentUser", userService.getUser(authentication.getName()));
        }
        return "profile";
    }

    @PostMapping("/user/{id}")
    public String addPost(@RequestParam String text,
                          @PathVariable Long id,
                          Authentication authentication) {
        User author = userService.getUser(authentication.getName());
        User userPage = userService.getUser(id);
        postService.addPost(text, author, userPage);
        return "redirect:/user/" + userPage.getId();
    }

    @GetMapping("/user/me")
    public String currentUserProfile(Model model, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        List<Post> posts = user.getPostsOnPage();
        Collections.reverse(posts);
        model.addAttribute("find", true);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("currentUser", user);
        return "profile";
    }

}
