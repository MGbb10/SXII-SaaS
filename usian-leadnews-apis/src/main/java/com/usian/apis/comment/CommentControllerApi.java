package com.usian.apis.comment;

import com.usian.model.comment.dtos.CommentDto;
import com.usian.model.comment.dtos.CommentSaveDto;
import com.usian.model.common.dtos.ResponseResult;

public interface CommentControllerApi {
    /**
     * 保存评论
     * @param dto
     * @return
     */
    public ResponseResult saveComment(CommentSaveDto dto);

    /**
     * 查询评论
     * @param dto
     * @return
     */
    public ResponseResult findByArticleId(CommentDto dto);
}
