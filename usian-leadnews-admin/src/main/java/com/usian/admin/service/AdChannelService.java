package com.usian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.admin.dtos.ChannelDto;
import com.usian.model.admin.pojos.AdChannel;
import com.usian.model.common.dtos.ResponseResult;

public interface AdChannelService extends IService<AdChannel> {
    /**
     * 分页查询
     * @param dto
     * @return
     */
    ResponseResult findByNameAndPage(ChannelDto dto);

    /**
     * 添加
     * @param channel
     * @return
     */
    public ResponseResult insert(AdChannel channel);

    /**
     * 修改
     * @param adChannel
     * @return
     */
    public ResponseResult update(AdChannel adChannel);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);
}
