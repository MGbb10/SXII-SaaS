package com.usian.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.article.dtos.ArticleHomeDto;
import com.usian.model.article.pojos.ApArticle;
import com.usian.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {
    ResponseResult load(ArticleHomeDto dto,Short loadType);
}
