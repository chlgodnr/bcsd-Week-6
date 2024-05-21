package com.example.demo.Service;

import com.example.demo.Entity.Article;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Optional<Article> deleteArticle(Long id) {
        return articleRepository.delete(id);
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }
}
