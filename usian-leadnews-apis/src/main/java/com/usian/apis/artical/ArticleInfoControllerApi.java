package com.usian.apis.artical;

import com.usian.model.article.dtos.ArticleInfoDto;
import com.usian.model.common.dtos.ResponseResult;

public interface ArticleInfoControllerApi {
    /**
     * 加载文章详情
     * @param dto
     * @return
     */
    public ResponseResult loadArtcleInfo(ArticleInfoDto dto);

    /**
     * 加载文章详情的行为内存
     * @param dto
     * @return
     */
    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
