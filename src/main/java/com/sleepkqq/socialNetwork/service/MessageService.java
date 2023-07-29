package com.sleepkqq.socialNetwork.service;

import com.sleepkqq.socialNetwork.model.Message;
import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    public void sendMessage(String text, String authorUsername, Long receiverId) {
        Message message = new Message();
        message.setText(text);
        message.setAuthor(userService.getUser(authorUsername));
        message.setReceiver(userService.getUser(receiverId));
        message.setDate(new Date());
        messageRepository.save(message);
        log.info("Message sent author: {}, receiver: {}", authorUsername, userService.getUser(receiverId).getUsername());
    }

    public List<User> usersWithMessages(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return messageRepository.findUsersWithMessagesForCurrentUser(user);
    }

    public List<Message> messagesCurrentUserWithReceiver(User currentUser, User receiver) {
        return messageRepository.findMessagesBetweenUsersOrderByDate(currentUser, receiver);
    }

}
