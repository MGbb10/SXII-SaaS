package com.usian.apis.admin;

import com.usian.model.admin.dtos.SensitiveDto;
import com.usian.model.admin.pojos.AdSensitive;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;

@Api(value = "敏感词管理", tags = "sensitive", description = "敏感词管理API")
public interface AdSensitiveControllerApi {
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
    public ResponseResult save(AdSensitive adSensitive);

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
