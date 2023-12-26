package com.usian.user.service;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.LoginDto;

public interface ApUserLoginService {
    /**
     * app端登录
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
