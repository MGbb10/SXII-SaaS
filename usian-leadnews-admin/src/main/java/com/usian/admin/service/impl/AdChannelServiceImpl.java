package com.usian.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.admin.mapper.AdChannelMapper;
import com.usian.admin.service.AdChannelService;
import com.usian.model.admin.dtos.ChannelDto;
import com.usian.model.admin.pojos.AdChannel;
import com.usian.model.common.dtos.PageResponseResult;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        //1.参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断对象参数是否有问题，如果参数没有传递，给默认值
        dto.checkParam();
        ;
        //3.先创建分页对象
        Page page = new Page(dto.getPage(), dto.getSize());
        //4.完成条件查询
        LambdaQueryWrapper<AdChannel> wrapper = new LambdaQueryWrapper<>();
        //不为空，就将name作为查询条件构建
        if (StringUtils.isNoneBlank(dto.getName())) {
            wrapper.like(AdChannel::getName, dto.getName());
        }
        //5.做分页查询
        IPage result = page(page, wrapper);
        //6.做结果封装
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) result.getTotal());
        //7.将查询分页结果赋值到对象中
        responseResult.setData(result.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult insert(AdChannel channel) {
        //1.检查参数
        if (null == channel) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.保存
        channel.setCreatedTime(new Date());
        save(channel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult update(AdChannel adChannel) {
        //1.检查参数
        if (null == adChannel || adChannel.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改
        updateById(adChannel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断当前频道是否存在 和 是否有效
        AdChannel adChannel = getById(id);
        if (adChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        if (adChannel.getStatus()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道有效不能删除");
        }
        //3.删除频道
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
