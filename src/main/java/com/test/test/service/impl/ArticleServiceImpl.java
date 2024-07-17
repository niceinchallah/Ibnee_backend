package com.test.test.service.impl;

import com.test.test.DTO.ArticleDTO;
import com.test.test.EXCEPTION.ResourceNotFoundException;
import com.test.test.entity.Article;
import com.test.test.mapper.ArticleMapper;
import com.test.test.repository.ArticleRepository;
import com.test.test.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = ArticleMapper.mapToArticle(articleDTO);
        Article savedArticle = articleRepository.save(article);
        return ArticleMapper.mapToArticleDTO(savedArticle);
    }

    @Override
    public ArticleDTO getArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));
        return ArticleMapper.mapToArticleDTO(article);
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleMapper::mapToArticleDTO).collect(Collectors.toList());
    }

    @Override
    public ArticleDTO updateArticle(Long articleId, ArticleDTO updatedArticleDTO) {
        Article existingArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));

        existingArticle.setTitle(updatedArticleDTO.getTitle());
        existingArticle.setDescription(updatedArticleDTO.getDescription());
        existingArticle.setPrice(updatedArticleDTO.getPrice());
        existingArticle.setQuantity(updatedArticleDTO.getQuantity());

        Article updatedArticle = articleRepository.save(existingArticle);
        return ArticleMapper.mapToArticleDTO(updatedArticle);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));
        articleRepository.deleteById(articleId);
    }
}
