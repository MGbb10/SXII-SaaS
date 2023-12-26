package com.usian.wemedia.controller.v1;

import com.usian.apis.wemedia.WmMaterialControllerApi;
import com.usian.common.constants.wemedia.WemediaContans;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmMaterialDto;
import com.usian.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController implements WmMaterialControllerApi {
    @Autowired
    private WmMaterialService materialService;
    @PostMapping("/upload_picture")
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return materialService.uploadPicture(multipartFile);
    }

    @RequestMapping("/list")
    @Override
    public ResponseResult findList(@RequestBody WmMaterialDto dto) {
        return materialService.findList(dto);
    }

    @GetMapping("/del_picture/{id}")
    @Override
    public ResponseResult delPicture(@PathVariable("id") Integer id) {
        return materialService.delPicture(id);
    }

    @GetMapping("/cancle_collection/{id}")
    @Override
    public ResponseResult cancleCollectionMaterial(@PathVariable("id") Integer id) {
        return materialService.updateStatus(id, WemediaContans.CANCLE_COLLECT_MATERIAL);
    }

    @GetMapping("/cllect/{id}")
    @Override
    public ResponseResult collectionMaterial(@PathVariable("id") Integer id) {
        return materialService.updateStatus(id,WemediaContans.COLLECT_MATERIAL);
    }
}
