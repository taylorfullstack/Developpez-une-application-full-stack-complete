package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ArticleRepository articleRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found with id " + comment.getUserId()));
                    CommentDTO commentDTO = commentMapper.toDTO(comment);
                    commentDTO.setUsername(user.getUsername()); // Set the authorName
                    return commentDTO;
                })
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        if (!userRepository.existsById(commentDTO.getUserId()) || !articleRepository.existsById(commentDTO.getArticleId())) {
            throw new EntityNotFoundException();
        }

        Comment comment = commentMapper.toEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);

        Article article = articleRepository.findById(commentDTO.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id " + commentDTO.getArticleId()));

        article.getCommentIds().add(savedComment.getId());
        articleRepository.save(article);

        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + commentDTO.getUserId()));
        CommentDTO savedCommentDTO = commentMapper.toDTO(savedComment);
        savedCommentDTO.setUsername(user.getUsername());

        return savedCommentDTO;
    }
}