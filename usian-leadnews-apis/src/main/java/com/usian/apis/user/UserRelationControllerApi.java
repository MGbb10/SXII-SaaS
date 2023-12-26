package com.usian.apis.user;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.UserRelationDto;

public interface UserRelationControllerApi {
    /**
     * 关注或取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}
