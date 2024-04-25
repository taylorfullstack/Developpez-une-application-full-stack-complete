package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    /*Mock the subscribed themes to test that the endpoint works*/
//    @GetMapping("/articles")
//    public List<ArticleDTO> getArticlesForSubscribedThemes() {
//        // Hardcoded list of themeIds for testing feature before real users are added
//        List<Long> themeIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
//        return articleService.getArticlesForSubscribedThemes(themeIds);
//    }

    @GetMapping
    public List<ArticleDTO> getArticlesForSubscribedThemes(Authentication authentication) {
        UserDTO userDTO = userService.getCurrentUser(authentication);
        List<Long> themeIds = userDTO.getSubscribedThemeIds();
        return articleService.getArticlesForSubscribedThemes(themeIds);
    }

    @GetMapping("/{id}")
    public Optional<ArticleDTO> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public ArticleDTO createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }
}
