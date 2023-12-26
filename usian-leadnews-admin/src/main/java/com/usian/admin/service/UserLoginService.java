package com.usian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.admin.dtos.AdUserDto;
import com.usian.model.admin.pojos.AdUser;
import com.usian.model.common.dtos.ResponseResult;

public interface UserLoginService extends IService<AdUser> {
    /**
     * 登录功能
     * @param dto
     * @return
     */
    ResponseResult login(AdUserDto dto);
}
