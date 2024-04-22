package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.services.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /*Mock the subscribed themes to test that the endpoint works*/
    @GetMapping("/articles")
    public List<ArticleDTO> getArticlesForSubscribedThemes() {
        // Hardcoded list of themeIds for testing feature before real users are added
        List<Long> themeIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        return articleService.getArticlesForSubscribedThemes(themeIds);
    }

//    @GetMapping("/articles")
//    public List<ArticleDTO> getArticlesForSubscribedThemes(@RequestParam("themeIds") List<Long> themeIds) {
//        return articleService.getArticlesForSubscribedThemes(themeIds);
//    }

    @GetMapping("/articles/{id}")
    public Optional<ArticleDTO> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

//    @PostMapping("/articles")
//    public ArticleDTO createArticle(@RequestBody ArticleDTO articleDTO) {
//        return articleService.createArticle(articleDTO);
//    }
}
