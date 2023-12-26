package com.usian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.common.dtos.PageResponseResult;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.AuthDto;
import com.usian.model.user.pojos.ApUserRealname;

public interface ApUserRealnameService extends IService<ApUserRealname> {

    /**
     * 根据状态查询需要认证相关的用户信息
     * @param dto
     * @return
     */
    PageResponseResult loadListByStatus(AuthDto dto);

    /**
     * 根据当前状态，完成用户认证状态的修改，并完成认证成功后的创建自媒体账号和作者账号
     * @param dto
     * @param status
     * @return
     */
    ResponseResult updateStatusById(AuthDto dto,Short status);
}
