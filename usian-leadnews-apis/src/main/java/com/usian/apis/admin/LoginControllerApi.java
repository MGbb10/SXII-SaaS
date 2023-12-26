package com.usian.apis.admin;

import com.usian.model.admin.dtos.AdUserDto;
import com.usian.model.admin.pojos.AdUser;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "登录管理", tags = "login", description = "登录管理API")
public interface LoginControllerApi {
    /**
     * admin登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(@RequestBody AdUserDto dto);
}
