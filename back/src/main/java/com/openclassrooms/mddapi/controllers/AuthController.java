package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.LoginDTO;
import com.openclassrooms.mddapi.dto.RegisterDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.security.JWTService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JWTService jwtService;
    private final UserService userService;

    public AuthController(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    //TODO add register method back once login issue is resolved

//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerDTO) {
//        userService.registerNewUser(registerDTO);
//        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setEmail(registerDTO.getEmail());
//        loginDTO.setPassword(registerDTO.getPassword());
//        Authentication authentication = userService.authenticate(loginDTO);
//        String token = jwtService.generateToken(authentication);
//        return ResponseEntity.ok(Collections.singletonMap("token", token));
//    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
            logger.info("Attempting to login user: {}", loginDTO.getEmail()); // Log statement
            Authentication authentication = userService.authenticate(loginDTO);
            String token = jwtService.generateToken(authentication);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException e) {
            logger.error("Failed to authenticate user: {}", loginDTO.getEmail(), e); // Log statement
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        UserDTO userDTO = userService.getCurrentUser(authentication);
        return ResponseEntity.ok(userDTO);
    }

}