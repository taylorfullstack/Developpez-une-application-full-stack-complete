package com.openclassrooms.mddapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class CommentDTO {
    private Long id;
    private String content;
    private Long userId;
    private String username;
    private Long articleId;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
