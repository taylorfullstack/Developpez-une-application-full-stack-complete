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
        String testEmail = "test@mdd.com";
        User userFromDb = userRepository.findByEmail(testEmail);
        Optional<User> existingUser = Optional.ofNullable(userFromDb);

        if (!existingUser.isPresent()) {
            User user = new User();
            user.setUsername("testuser");
            user.setEmail(testEmail);
            user.setPassword(bCryptPasswordEncoder.encode("Mdd@123!"));
            userRepository.save(user);
        }
    }

}
