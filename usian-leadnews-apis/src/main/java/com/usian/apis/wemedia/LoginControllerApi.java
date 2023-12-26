package com.usian.apis.wemedia;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmUserDto;

public interface LoginControllerApi {
    /**
     * 自媒体登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmUserDto dto);
}
