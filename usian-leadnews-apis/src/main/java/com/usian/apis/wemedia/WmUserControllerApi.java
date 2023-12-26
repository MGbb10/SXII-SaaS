package com.usian.apis.wemedia;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.pojos.WmUser;

public interface WmUserControllerApi {
    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    public ResponseResult save(WmUser wmUser);

    /**
     * 按照名称查询用户
     * @param name
     * @return
     */
    public WmUser findByName(String name);

    /**
     * 根据id查询自媒体用户
     * @param id
     * @return
     */
    WmUser findWmUserById(Long id);
}
