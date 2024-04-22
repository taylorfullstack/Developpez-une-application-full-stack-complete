package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(source = "theme.title", target = "themeTitle")
    @Mapping(source = "user.name", target = "authorName")
    @Mapping(source = "user.id", target = "authorId")
    @Mapping(source = "theme.id", target = "themeId")
    ArticleDTO toDTO(Article article);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "theme", ignore = true)
    Article toEntity(ArticleDTO articleDTO);
}