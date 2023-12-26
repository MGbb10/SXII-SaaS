package com.usian.apis.artical;

import com.usian.model.article.pojos.ApArticle;

public interface ApArticleControllerApi {
    /**
     * 保存app文章
     * @param article
     * @return
     */
    ApArticle saveArticle(ApArticle article);
}
