package com.usian.apis.behavior;

import com.usian.model.behavior.dtos.LikesBehaviorDto;
import com.usian.model.behavior.pojos.ApLikesBehavior;
import com.usian.model.common.dtos.ResponseResult;

public interface ApLikesBehaviorControllerApi {
    /**
     * 保存点赞行为
     * @param dto
     * @return
     */
    public ResponseResult like(LikesBehaviorDto dto);


    public ApLikesBehavior findByArticleIdAndEntryId(Long articleId,Integer entryId,Short type);
}
