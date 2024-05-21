package com.example.demo.Controller;

import com.example.demo.Entity.Article;
import com.example.demo.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@Service
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }


    //@Autowired
    //public ArticleController()
    // Article 생성
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    // Article 조회
    @GetMapping("/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }


// Article 수정
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        Optional<Article> articleOptional =articleService.findArticleById(id);

        if (!articleOptional.isPresent())
            return ResponseEntity.notFound().build();
        Article article = articleOptional.get();
        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());

        articleService.saveArticle(article);
        return ResponseEntity.ok(article);
    }

    // Article 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        Optional<Article> articleOptional = articleService.deleteArticle(id);
        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        }
    }


    //모든 Article 조회
    @GetMapping("/posts")
    public ResponseEntity<List<Article>> getallArticle(){
        List<Article> articles =  articleService.findAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articles);
    }
}

