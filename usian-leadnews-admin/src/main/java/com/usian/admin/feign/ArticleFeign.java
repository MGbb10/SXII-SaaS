package com.usian.admin.feign;

import com.usian.model.article.pojos.ApArticleConfig;
import com.usian.model.article.pojos.ApArticleContent;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("leadnews-article")
public interface ArticleFeign {
    //新增APP已发布文章配置
    @PostMapping("/api/v1/article_config/save")
    ResponseResult saveArticleConfig(ApArticleConfig apArticleConfig);

    //根据姓名查询APP文章作者信息
    @GetMapping("/api/v1/article_content/save")
    ResponseResult saveArticleContent(ApArticleContent apArticleContent);
}
