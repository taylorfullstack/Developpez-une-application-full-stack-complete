package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "commenter.id", target = "commenterId")
    CommentDTO toDTO(Comment comment);

    @Mapping(source = "commenterId", target = "commenter.id")
    Comment toEntity(CommentDTO commentDTO);
}