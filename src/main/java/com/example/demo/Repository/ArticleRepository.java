package com.example.demo.Repository;

import com.example.demo.Entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    Optional<Article> delete(Long id);
    List<Article> findAll();
}
