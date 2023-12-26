package com.usian.comment.service;

import com.usian.model.comment.dtos.CommentDto;
import com.usian.model.comment.dtos.CommentSaveDto;
import com.usian.model.common.dtos.ResponseResult;

public interface CommentService {
    public ResponseResult saveComment(CommentSaveDto dto);

    /**
     * 根据文章id查询评论列表
     * @param dto
     * @return
     */
    public ResponseResult findByArticleId(CommentDto dto);
}
