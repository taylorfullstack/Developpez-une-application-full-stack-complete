package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.models.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDTO toDTO(Theme theme);
    Theme toEntity(ThemeDTO themeDTO);
}