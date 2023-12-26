package com.usian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.admin.dtos.SensitiveDto;
import com.usian.model.admin.pojos.AdSensitive;
import com.usian.model.common.dtos.ResponseResult;

public interface AdSensitiveService extends IService<AdSensitive> {
    /**
     * 根据名称分页查询敏感词
     *
     * @param dto
     * @return
     */
    public ResponseResult list(SensitiveDto dto);

    /**
     * 新增
     *
     * @param adSensitive
     * @return
     */
    public ResponseResult insert(AdSensitive adSensitive);

    /**
     * 修改
     *
     * @param adSensitive
     * @return
     */
    public ResponseResult update(AdSensitive adSensitive);

    /**
     * 删除敏感词
     *
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);
}
