package com.test.test.mapper;

import com.test.test.DTO.ArticleDTO;
import com.test.test.entity.Article;

public class ArticleMapper {

    public static ArticleDTO mapToArticleDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setPrice(article.getPrice());
        articleDTO.setQuantity(article.getQuantity());
        return articleDTO;
    }

    public static Article mapToArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setDescription(articleDTO.getDescription());
        article.setPrice(articleDTO.getPrice());
        article.setQuantity(articleDTO.getQuantity());
        return article;
    }
}
