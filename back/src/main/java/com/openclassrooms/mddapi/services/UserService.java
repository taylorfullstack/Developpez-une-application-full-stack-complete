package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.LoginDTO;
import com.openclassrooms.mddapi.dto.RegisterDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final UserMapper userMapper;
    private final ThemeMapper themeMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, ThemeRepository themeRepository,
                       UserMapper userMapper, ThemeMapper themeMapper,
                       BCryptPasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.userMapper = userMapper;
        this.themeMapper = themeMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO registerNewUser(RegisterDTO registerDTO) {
        User existingUser = userRepository.findByEmail(registerDTO.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        existingUser = userRepository.findByUsername(registerDTO.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username is already in use");
        }

        if (registerDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public Authentication authenticate(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmailOrUsername());
        if (user == null) {
            user = userRepository.findByUsername(loginDTO.getEmailOrUsername());
        }
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or username");
        }
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmailOrUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid password");
        }
    }

    public UserDTO getCurrentUser(Authentication authentication) {
        String emailOrUsername = authentication.getName();
        User user = userRepository.findByEmail(emailOrUsername);
        if (user == null) {
            user = userRepository.findByUsername(emailOrUsername);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userMapper.toDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO, Authentication authentication) {
        UserDTO currentUserDTO = getCurrentUser(authentication);
        User user = userRepository.findById(currentUserDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserDTO subscribeToTheme(Long themeId, Authentication authentication) {
        UserDTO currentUserDTO = getCurrentUser(authentication);
        User user = userRepository.findById(currentUserDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!themeRepository.existsById(themeId)) {
            throw new EntityNotFoundException("Theme not found");
        }
        if (user.getSubscribedThemeIds().contains(themeId)) {
            throw new IllegalArgumentException("User is already subscribed to this theme");
        }
        user.getSubscribedThemeIds().add(themeId);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserDTO unsubscribeFromTheme(Long themeId, Authentication authentication) {
        UserDTO currentUserDTO = getCurrentUser(authentication);
        User user = userRepository.findById(currentUserDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!themeRepository.existsById(themeId)) {
            throw new EntityNotFoundException("Theme not found");
        }
        if (!user.getSubscribedThemeIds().contains(themeId)) {
            throw new IllegalArgumentException("User is not subscribed to this theme");
        }
        user.getSubscribedThemeIds().remove(themeId);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
}