package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestUserInitializerService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TestUserInitializerService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent ignoredEvent) {
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals("user@mdd.com"))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
}
