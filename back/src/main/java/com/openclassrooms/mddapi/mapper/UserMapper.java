package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}