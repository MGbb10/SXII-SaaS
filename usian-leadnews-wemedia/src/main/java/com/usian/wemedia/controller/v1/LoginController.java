package com.usian.wemedia.controller.v1;

import com.usian.apis.wemedia.LoginControllerApi;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.dtos.WmUserDto;
import com.usian.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {
    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/in")
    @Override
    public ResponseResult login(@RequestBody WmUserDto dto) {
        return wmUserService.login(dto);
    }
}
