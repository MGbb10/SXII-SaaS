package com.usian.wemedia.controller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.usian.apis.wemedia.WmUserControllerApi;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import com.usian.model.media.pojos.WmUser;
import com.usian.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class WmUserController implements WmUserControllerApi {
    @Autowired
    private WmUserService userService;

    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody WmUser wmUser) {
        userService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @GetMapping("/findByName/{name}")
    @Override
    public WmUser findByName(@PathVariable("name") String name) {
        List<WmUser> list = userService.list(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @GetMapping("/findOne/{id}")
    @Override
    public WmUser findWmUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
