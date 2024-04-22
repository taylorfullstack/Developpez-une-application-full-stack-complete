package com.openclassrooms.mddapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private Long authorId;
    private String themeTitle;
    private Long themeId;
    private List<Long> commentIds;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
