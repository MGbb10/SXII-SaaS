package com.usian.apis.wemedia;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmNewsDto;
import com.usian.model.media.dtos.WmNewsPageReqDto;
import com.usian.model.media.pojos.WmNews;

public interface WmNewsControllerApi {
    /**
     * 分页查询自媒体文章
     * @param wmNewsPageReqDto
     * @return
     */
    public ResponseResult  findAll(WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * 提交文章
     * @param wmNewsDto
     * @return
     */
    public ResponseResult summitNmews(WmNewsDto wmNewsDto);

    /**
     * 根据id获取文章信息
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

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    WmNews findById(Integer id);

    /**
     * 修改文章
     * @param wmNews
     * @return
     */
    ResponseResult updateWmNews(WmNews wmNews);
}
