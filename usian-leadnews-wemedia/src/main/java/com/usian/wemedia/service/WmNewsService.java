package com.usian.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmNewsDto;
import com.usian.model.media.dtos.WmNewsPageReqDto;
import com.usian.model.media.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {
    /**
     * 分页查询自媒体文章
     * @param wmNewsPageReqDto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * 自媒体文章发布
     * @param wmNewsDto
     * @param isSubmit 是否提交 1为提交 0为草稿
     * @return
     */
    public ResponseResult saveNews(WmNewsDto wmNewsDto,Short isSubmit);

    /**
     * 根据文章id查询文章
     * @param id
     * @return
     */
    public ResponseResult findWmNewsById(Integer id);
    /**
     * 删除文章
     * @param id
     * @return
     */
    public ResponseResult delNews(Integer id);

    /**
     * 上下架
     * @param dto
     * @return
     */
    public ResponseResult downOrUp(WmNewsDto dto);
}
