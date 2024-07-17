package com.test.test.controller;

import com.test.test.DTO.ArticleDTO;
import com.test.test.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@Validated
public class Articlecontroller {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO savedArticle = articleService.createArticle(articleDTO);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id") Long articleId) {
        ArticleDTO articleDTO = articleService.getArticleById(articleId);
        return ResponseEntity.ok(articleDTO);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articleList = articleService.getAllArticles();
        return ResponseEntity.ok(articleList);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable("id") Long articleId,
                                                    @RequestBody ArticleDTO updatedArticle) {
        ArticleDTO articleDTO = articleService.updateArticle(articleId, updatedArticle);
        return ResponseEntity.ok(articleDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
