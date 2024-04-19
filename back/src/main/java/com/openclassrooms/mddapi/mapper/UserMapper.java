package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.models.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "subscribedThemes", target = "subscribedThemeIds", qualifiedByName = "themesToLongs")
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

    @Named("themesToLongs")
    default List<Long> themesToLongs(List<Theme> themes) {
        return themes.stream()
                .map(this::themeToLong)
                .collect(Collectors.toList());
    }

    default Long themeToLong(Theme theme) {
        return theme.getId();
    }
}