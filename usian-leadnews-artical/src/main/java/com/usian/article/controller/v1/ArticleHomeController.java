package com.usian.article.controller.v1;

import com.usian.apis.artical.ArticleHomeControllerApi;
import com.usian.article.service.ApArticleService;
import com.usian.common.constants.article.ArticleConstants;
import com.usian.model.article.dtos.ArticleHomeDto;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController implements ArticleHomeControllerApi {
    @Autowired
    private ApArticleService apArticleService;

    @PostMapping("/load")
    @Override
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    @PostMapping("/loadmore")
    @Override
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto,ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    @PostMapping("/loadnew")
    @Override
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto,ArticleConstants.LOADTYPE_LOAD_NEW);
    }
}
