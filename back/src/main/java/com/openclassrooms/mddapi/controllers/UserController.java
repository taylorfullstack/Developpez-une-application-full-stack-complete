package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/themes/{themeId}")
    public UserDTO subscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
        return userService.subscribeToTheme(userId, themeId);
    }

    @DeleteMapping("/{userId}/themes/{themeId}")
    public UserDTO unsubscribeFromTheme(@PathVariable Long userId, @PathVariable Long themeId) {
        return userService.unsubscribeFromTheme(userId, themeId);
    }

    @GetMapping("/{userId}/themes")
    public List<ThemeDTO> getSubscribedThemes(@PathVariable Long userId) {
        return userService.getSubscribedThemes(userId);
    }
}