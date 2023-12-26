package com.usian.apis.admin;

import com.usian.model.admin.dtos.ChannelDto;
import com.usian.model.admin.pojos.AdChannel;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "频道管理", tags = "channel", description = "频道管理API")
public interface AdChannelControllerApi {
    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    @ApiOperation("频道分页列表查询")
    public ResponseResult findByNameAndPage(ChannelDto dto);

    /**
     * 新增
     * @param channel
     * @return
     */
    @ApiOperation("频道新增")
    public ResponseResult save(AdChannel channel);

    /**
     * 修改
     * @param adChannel
     * @return
     */
    @ApiOperation("频道修改")
    public ResponseResult update(AdChannel adChannel);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("频道删除")
    public ResponseResult deleteById(Integer id);

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();
}
