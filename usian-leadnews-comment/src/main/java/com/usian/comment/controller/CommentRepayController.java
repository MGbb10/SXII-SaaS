package com.usian.comment.controller;

import com.usian.apis.comment.CommentRepayControllerApi;
import com.usian.comment.service.CommentRepayService;
import com.usian.model.comment.dtos.CommentRepayDto;
import com.usian.model.comment.dtos.CommentRepayLikeDto;
import com.usian.model.comment.dtos.CommentRepaySaveDto;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment_repay")
public class CommentRepayController implements CommentRepayControllerApi {
    @Autowired
    private CommentRepayService commentRepayService;

    @PostMapping("/load")
    @Override
    public ResponseResult loadCommentRepay(@RequestBody CommentRepayDto dto) {
        return commentRepayService.loadCommentRepay(dto);
    }

    @PostMapping("/save")
    @Override
    public ResponseResult saveCommentRepay(@RequestBody CommentRepaySaveDto dto) {
        return commentRepayService.saveCommentRepay(dto);
    }

    @PostMapping("/like")
    @Override
    public ResponseResult saveCommentRepayLike(@RequestBody CommentRepayLikeDto dto) {
        return commentRepayService.saveCommentRepayLike(dto);
    }
}
