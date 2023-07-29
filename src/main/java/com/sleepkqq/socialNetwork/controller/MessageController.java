package com.sleepkqq.socialNetwork.controller;

import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.service.MessageService;
import com.sleepkqq.socialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @GetMapping("/messenger")
    public String messenger(Authentication authentication, Model model) {
        List<User> usersWithMessages = messageService.usersWithMessages(authentication);
        model.addAttribute("users", usersWithMessages);
        model.addAttribute("currentUser", userService.getUser(authentication.getName()));
        return "messenger";
    }

    @PostMapping("/messenger/user/{id}")
    public String sendMessage(@RequestParam String text,
                              @PathVariable Long id,
                              Authentication authentication) {
        messageService.sendMessage(text, authentication.getName(), id);
        return "redirect:/messenger/user/" + id;
    }

    @GetMapping("/messenger/user/{id}")
    public String getDialog(@PathVariable Long id,
                            Authentication authentication,
                            Model model) {
        User userNow =  userService.getUser(authentication.getName());
        User receiver = userService.getUser(id);
        model.addAttribute("receiver", receiver);
        model.addAttribute("userNow", userNow);
        model.addAttribute("messages", messageService.messagesCurrentUserWithReceiver(userNow, receiver));
        return "chat";
    }

}
