package com.usian.apis.behavior;

import com.usian.model.behavior.pojos.ApUnlikesBehavior;

public interface ApUnlikesBehaviorControllerApi {
    /**
     * 根据行为实体id和文章id查询不喜欢行为
     * @param entryId
     * @param articleId
     * @return
     */
    public ApUnlikesBehavior findUnLikeByArticleIdAndEntryId(Integer entryId,Long articleId);
}
