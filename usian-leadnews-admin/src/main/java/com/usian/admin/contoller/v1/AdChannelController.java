package com.usian.admin.contoller.v1;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.usian.admin.service.AdChannelService;
import com.usian.apis.admin.AdChannelControllerApi;
import com.usian.model.admin.dtos.ChannelDto;
import com.usian.model.admin.pojos.AdChannel;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
@Api(value = "频道管理", tags = "channel", description = "频道管理API")
public class AdChannelController implements AdChannelControllerApi {
    @Autowired
    private AdChannelService adChannelService;

    @PostMapping("/list")
    @ApiOperation(value = "查询频道信息", notes = "根据名称分页查询频道列表")
    @ApiImplicitParam(name = "dto", type = "ChannelDto", value = "查询名称")
    // 多个参数用以下注解
    //@ApiOperation(value = "查询商品列表",notes = "分页查询")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "page",type ="Integer",value = "页码"),
    //        @ApiImplicitParam(name = "rows",type ="Integer",value = "条数")
    //  }
    //)
    @Override
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto) {
        return adChannelService.findByNameAndPage(dto);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增频道信息")
    @Override
    public ResponseResult save(@RequestBody AdChannel channel) {
        return adChannelService.insert(channel);
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改频道信息")
    @Override
    public ResponseResult update(@RequestBody AdChannel adChannel) {
        return adChannelService.update(adChannel);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "删除频道信息")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return adChannelService.deleteById(id);
    }


    @GetMapping("/channels")
    @Override
    public ResponseResult findAll() {
        LambdaQueryChainWrapper<AdChannel> list = adChannelService.lambdaQuery();
        return ResponseResult.okResult(list);
    }
}
