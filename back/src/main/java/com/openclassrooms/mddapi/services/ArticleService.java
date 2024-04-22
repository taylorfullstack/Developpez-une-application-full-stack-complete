package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, ThemeRepository themeRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDTO> getArticlesForSubscribedThemes(List<Long> themeIds) {
        List<ArticleDTO> articles = new ArrayList<>();
        for (Long themeId : themeIds) {
            List<Article> themeArticles = articleRepository.findByThemeId(themeId);
            for (Article article : themeArticles) {
                articles.add(articleMapper.toDTO(article));
            }
        }
        return articles;
    }

    public Optional<ArticleDTO> getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDTO);
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        if (!userRepository.existsById(articleDTO.getAuthorId()) || !themeRepository.existsById(articleDTO.getThemeId())) {
            throw new EntityNotFoundException();
        }

        Article article = articleMapper.toEntity(articleDTO);

        User author = userRepository.findById(articleDTO.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + articleDTO.getAuthorId()));
        article.setUser(author);

        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDTO(savedArticle);
    }
}