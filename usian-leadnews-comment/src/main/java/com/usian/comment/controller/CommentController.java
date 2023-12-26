package com.usian.comment.controller;

import com.usian.apis.comment.CommentControllerApi;
import com.usian.comment.service.CommentService;
import com.usian.model.comment.dtos.CommentDto;
import com.usian.model.comment.dtos.CommentSaveDto;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController implements CommentControllerApi {
    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    @Override
    public ResponseResult saveComment(@RequestBody CommentSaveDto dto) {
        return commentService.saveComment(dto);
    }

    @PostMapping("/load")
    @Override
    public ResponseResult findByArticleId(@RequestBody CommentDto dto) {
        return commentService.findByArticleId(dto);
    }
}
