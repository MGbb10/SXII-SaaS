package com.usian.apis.artical;

import com.usian.model.article.dtos.ArticleHomeDto;
import com.usian.model.common.dtos.ResponseResult;

public interface ArticleHomeControllerApi {
    /**
     * 加载首页文章
     *
     * @param dto
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto);

    /**
     * 加载更多上拉
     *
     * @param dto
     * @return
     */
    public ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * 加载最新下拉
     * @param dto
     * @return
     */
    public ResponseResult loadNew(ArticleHomeDto dto);
}
