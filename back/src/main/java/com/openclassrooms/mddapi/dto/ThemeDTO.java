package com.openclassrooms.mddapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ThemeDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
