package com.usian.apis.user;

import com.usian.model.common.dtos.PageResponseResult;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.AuthDto;

public interface ApUserRealnameControllerApi {
    /**
     * 按状态查询用户认证列表
     * @param dto
     * @return
     */
    public PageResponseResult loadListByStatus(AuthDto dto);

    /**
     * 审核通过
     * @param dto
     * @return
     */
    public ResponseResult authPass(AuthDto dto);

    /**
     * 审核失败
     * @param dto
     * @return
     */
    public ResponseResult authFail(AuthDto dto);
}
