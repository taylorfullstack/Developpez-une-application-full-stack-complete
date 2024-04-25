package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/me")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/themes/{themeId}")
    public UserDTO subscribeToTheme(@PathVariable Long themeId, Authentication authentication) {
        UserDTO currentUser = userService.getCurrentUser(authentication);
        return userService.subscribeToTheme(currentUser.getId(), themeId);
    }

    @DeleteMapping("/themes/{themeId}")
    public UserDTO unsubscribeFromTheme(@PathVariable Long themeId, Authentication authentication) {
        UserDTO currentUser = userService.getCurrentUser(authentication);
        return userService.unsubscribeFromTheme(currentUser.getId(), themeId);
    }

    @GetMapping("/themes")
    public List<ThemeDTO> getSubscribedThemes(Authentication authentication) {
        UserDTO currentUser = userService.getCurrentUser(authentication);
        return userService.getSubscribedThemes(currentUser.getId());
    }
}