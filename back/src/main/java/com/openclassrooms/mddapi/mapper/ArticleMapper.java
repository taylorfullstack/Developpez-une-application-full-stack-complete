package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO toDTO(Article article);

    @Mapping(target = "id", ignore = true)
    Article toEntity(ArticleDTO articleDTO);
}
