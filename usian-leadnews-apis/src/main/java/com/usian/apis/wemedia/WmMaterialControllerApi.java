package com.usian.apis.wemedia;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmMaterialDto;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialControllerApi {
    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表
     * @param dto
     * @return
     */
    ResponseResult findList(WmMaterialDto dto);

    /**
     * 删除图片
     * @param id
     * @return
     */
    ResponseResult delPicture(Integer id);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    ResponseResult cancleCollectionMaterial(Integer id);

    /**
     * 收藏图片
     * @param id
     * @return
     */
    ResponseResult collectionMaterial(Integer id);
}
