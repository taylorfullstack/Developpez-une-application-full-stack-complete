package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final UserMapper userMapper;
    private final ThemeMapper themeMapper;

    public UserService(UserRepository userRepository, ThemeRepository themeRepository, UserMapper userMapper, ThemeMapper themeMapper) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.userMapper = userMapper;
        this.themeMapper = themeMapper;
    }

    public UserDTO subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!themeRepository.existsById(themeId)) {
            throw new EntityNotFoundException("Theme not found");
        }
        user.getSubscribedThemeIds().add(themeId);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO unsubscribeFromTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
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
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final ThemeRepository themeRepository;
//    private final UserMapper userMapper;
//    private final ThemeMapper themeMapper;
//
//    public UserService(UserRepository userRepository, ThemeRepository themeRepository, UserMapper userMapper, ThemeMapper themeMapper) {
//        this.userRepository = userRepository;
//        this.themeRepository = themeRepository;
//        this.userMapper = userMapper;
//        this.themeMapper = themeMapper;
//    }
//
//    public UserDTO subscribeToTheme(Long userId, Long themeId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme not found"));
//        user.getSubscribedThemes().add(theme);
//        return userMapper.toDTO(userRepository.save(user));
//    }
//
//    public UserDTO unsubscribeFromTheme(Long userId, Long themeId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme not found"));
//        user.getSubscribedThemes().remove(theme);
//        return userMapper.toDTO(userRepository.save(user));
//    }
//
//    public List<ThemeDTO> getSubscribedThemes(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        return user.getSubscribedThemes().stream()
//                .map(themeMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//}
