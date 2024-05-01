package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
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
                User author = userRepository.findById(article.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found with id " + article.getUserId()));
                Theme theme = themeRepository.findById(article.getThemeId())
                        .orElseThrow(() -> new EntityNotFoundException("Theme not found with id " + article.getThemeId()));

                ArticleDTO articleDTO = articleMapper.toDTO(article);
                articleDTO.setUsername(author.getUsername());
                articleDTO.setThemeTitle(theme.getTitle());

                articles.add(articleDTO);
            }
        }
        return articles;
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        if (!userRepository.existsById(articleDTO.getUserId()) || !themeRepository.existsById(articleDTO.getThemeId())) {
            throw new EntityNotFoundException();
        }

        Article article = articleMapper.toEntity(articleDTO);
        article.setUserId(articleDTO.getUserId());
        article.setThemeId(articleDTO.getThemeId());

        article.setCommentIds(new ArrayList<>());

        Article savedArticle = articleRepository.save(article);

        User author = userRepository.findById(articleDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + articleDTO.getUserId()));
        Theme theme = themeRepository.findById(articleDTO.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with id " + articleDTO.getThemeId()));

        ArticleDTO savedArticleDTO = articleMapper.toDTO(savedArticle);
        savedArticleDTO.setUsername(author.getUsername());
        savedArticleDTO.setThemeTitle(theme.getTitle());

        return savedArticleDTO;
    }

    public Optional<ArticleDTO> getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    User author = userRepository.findById(article.getUserId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found with id " + article.getUserId()));
                    Theme theme = themeRepository.findById(article.getThemeId())
                            .orElseThrow(() -> new EntityNotFoundException("Theme not found with id " + article.getThemeId()));

                    ArticleDTO articleDTO = articleMapper.toDTO(article);
                    articleDTO.setUsername(author.getUsername());
                    articleDTO.setThemeTitle(theme.getTitle());

                    return articleDTO;
                });
    }
}