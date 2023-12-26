package com.usian.article.controller.v1;

import com.usian.apis.artical.ApArticleControllerApi;
import com.usian.article.service.ApArticleService;
import com.usian.model.article.pojos.ApArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ApArticleController implements ApArticleControllerApi {
    @Autowired
    private ApArticleService apArticleService;
    @PostMapping("/save")
    @Override
    public ApArticle saveArticle(@RequestBody ApArticle article) {
        apArticleService.save(article);
        return article;
    }
}
