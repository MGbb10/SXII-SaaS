package com.usian.article.controller.v1;

import com.usian.apis.artical.ApArticleContentControllerApi;
import com.usian.article.service.ApArticleContentService;
import com.usian.model.article.pojos.ApArticleContent;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article_content")
public class ApArticleContentController implements ApArticleContentControllerApi {
    @Autowired
    private ApArticleContentService apArticleContentService;

    @PostMapping("/save")
    @Override
    public ResponseResult saveArticleContent(@RequestBody ApArticleContent apArticleContent) {
        apArticleContentService.save(apArticleContent);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
