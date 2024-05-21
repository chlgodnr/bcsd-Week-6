package com.example.demo.Repository;

import com.example.demo.Entity.Article;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryArticleRepository implements ArticleRepository{
    private final Map<Long, Article> articles = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Article save(Article article){
        long id = counter.incrementAndGet();
        article.setId(id);
        articles.put(id, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id){
       return Optional.ofNullable(articles.get(id));
    }

    @Override
    public Optional<Article> delete(Long id) {
        return Optional.ofNullable(articles.remove(id));
    }

        @Override
        public List<Article> findAll () {
            return new ArrayList<>(articles.values());
        }

    }


