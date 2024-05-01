package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);

    @Mapping(target = "id", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}