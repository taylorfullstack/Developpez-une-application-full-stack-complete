package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String emailOrUsername;
    private String password;
}