package com.usian.apis.user;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.LoginDto;

public interface ApUserLoginControllerApi {
    public ResponseResult login(LoginDto dto);
}
