package com.usian.behavior.controller.v1;

import com.usian.apis.behavior.ApReadBehaviorControllerApi;
import com.usian.behavior.service.ApReadBehaviorService;
import com.usian.model.behavior.dtos.ReadBehaviorDto;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pai/v1/read_behavior")
public class ApReadBehaviorController implements ApReadBehaviorControllerApi {
    @Autowired
    private ApReadBehaviorService apReadBehaviorService;

    @PostMapping
    @Override
    public ResponseResult readBehavior(@RequestBody ReadBehaviorDto dto) {
        return apReadBehaviorService.readBehavior(dto);
    }
}
