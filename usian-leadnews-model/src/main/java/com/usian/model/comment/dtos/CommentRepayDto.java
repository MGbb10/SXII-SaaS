package com.usian.model.comment.dtos;

import com.usian.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class CommentRepayDto extends PageRequestDto {

    /**
     * 评论id
     */
    private String commentId;

    // 最小时间
    private Date minDate;
}