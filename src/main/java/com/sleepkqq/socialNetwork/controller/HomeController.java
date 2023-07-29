package com.sleepkqq.socialNetwork.controller;

import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (isAuthenticated) {
            User currentUser = userService.getUser(authentication.getName());
            model.addAttribute("currentUser", currentUser);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "index";
    }

}
