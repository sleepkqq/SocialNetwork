package com.sleepkqq.socialNetwork.service;

import com.sleepkqq.socialNetwork.model.Role;
import com.sleepkqq.socialNetwork.model.User;
import com.sleepkqq.socialNetwork.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null || user.getUsername().length() > 10) {
            log.info("Ошибка при сохранении пользователя с username: {}", user.getUsername());
            return false;
        }
        user.setActive(true);
        user.setRole(Role.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        log.info("Сохранен пользователь с username: {}", user.getUsername());
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkUsernameAvailability(String username) {
        Map<String, Boolean> response = new HashMap<>();
        return !userRepository.existsByUsername(username);
    }

}
