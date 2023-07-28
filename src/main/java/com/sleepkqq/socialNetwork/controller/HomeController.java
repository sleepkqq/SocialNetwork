package com.sleepkqq.socialNetwork.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (isAuthenticated) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "index";
    }

}
