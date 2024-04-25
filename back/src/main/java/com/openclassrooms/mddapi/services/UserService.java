package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.controllers.AuthController;
import com.openclassrooms.mddapi.dto.LoginDTO;
import com.openclassrooms.mddapi.dto.RegisterDTO;
import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final UserMapper userMapper;
    private final ThemeMapper themeMapper;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    //, BCryptPasswordEncoder passwordEncoder
    public UserService(UserRepository userRepository, ThemeRepository themeRepository, UserMapper userMapper, ThemeMapper themeMapper) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.userMapper = userMapper;
        this.themeMapper = themeMapper;
//        this.passwordEncoder = passwordEncoder;
    }

//    public void registerNewUser(RegisterDTO registerDTO) {
//        User user = new User();
//        user.setEmail(registerDTO.getEmail());
//        user.setName(registerDTO.getName());
//        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
//        User savedUser = userRepository.save(user);
//        userMapper.toDTO(savedUser);
//    }


    public Authentication authenticate(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public UserDTO getCurrentUser(Authentication authentication) {
        logger.info("Attempting to get user with auth:", authentication);
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(authentication.getName()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    //TODO pass auth rather than user id to get user and their subscribed themes

    public UserDTO subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!themeRepository.existsById(themeId)) {
            throw new EntityNotFoundException("Theme not found");
        }
        //TODO check if user is already subscribed
        user.getSubscribedThemeIds().add(themeId);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO unsubscribeFromTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        //TODO check if user is already subscribed
        user.getSubscribedThemeIds().remove(themeId);
        return userMapper.toDTO(userRepository.save(user));
    }

    public List<ThemeDTO> getSubscribedThemes(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getSubscribedThemeIds().stream()
                .map(themeId -> themeRepository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme not found")))
                .map(themeMapper::toDTO)
                .collect(Collectors.toList());
    }
}