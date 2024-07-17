package com.test.test.service;

import com.test.test.DTO.ArticleDTO;

import java.util.List;

public interface ArticleService {
    ArticleDTO createArticle(ArticleDTO articleDTO);
    ArticleDTO getArticleById(Long articleId);
    List<ArticleDTO> getAllArticles();
    ArticleDTO updateArticle(Long articleId, ArticleDTO updatedArticleDTO);
    void deleteArticle(Long articleId);
}
