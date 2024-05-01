package com.openclassrooms.mddapi.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<Long> subscribedThemeIds;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
