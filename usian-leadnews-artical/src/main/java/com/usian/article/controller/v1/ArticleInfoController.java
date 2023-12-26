package com.usian.article.controller.v1;

import com.usian.apis.artical.ArticleInfoControllerApi;
import com.usian.article.service.ArticleInfoService;
import com.usian.model.article.dtos.ArticleInfoDto;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleInfoController implements ArticleInfoControllerApi {
    @Autowired
    private ArticleInfoService articleInfoService;

    @PostMapping("?load_article_info")
    @Override
    public ResponseResult loadArtcleInfo(@RequestBody ArticleInfoDto dto) {
        return articleInfoService.loadArticleInfo(dto);
    }

    @PostMapping("/load_article_behavior")
    @Override
    public ResponseResult loadArticleBehavior(@RequestBody ArticleInfoDto dto) {
        return articleInfoService.loadArticleBehavior(dto);
    }
}
