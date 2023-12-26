package com.usian.admin.contoller.v1;

import com.usian.admin.service.AdSensitiveService;
import com.usian.apis.admin.AdSensitiveControllerApi;
import com.usian.model.admin.dtos.SensitiveDto;
import com.usian.model.admin.pojos.AdSensitive;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sensitive")
public class AdSensitiveController implements AdSensitiveControllerApi {
    @Autowired
    private AdSensitiveService adSensitiveService;

    @PostMapping("/list")
    @ApiOperation(value = "敏感词分页查询")
    @Override
    public ResponseResult list(@RequestBody SensitiveDto dto) {
        return adSensitiveService.list(dto);
    }

    @PostMapping("/save")
    @ApiOperation(value = "敏感词新增")
    @Override
    public ResponseResult save(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.insert(adSensitive);
    }

    @PostMapping("/update")
    @ApiOperation(value = "敏感词修改")
    @Override
    public ResponseResult update(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.update(adSensitive);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "敏感词删除")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return adSensitiveService.deleteById(id);
    }
}
