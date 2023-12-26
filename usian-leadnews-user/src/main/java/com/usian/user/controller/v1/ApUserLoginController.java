package com.usian.user.controller.v1;

import com.usian.apis.user.ApUserLoginControllerApi;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.LoginDto;
import com.usian.user.service.ApUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class ApUserLoginController implements ApUserLoginControllerApi {
    @Autowired
    private ApUserLoginService apUserLoginService;

    @PostMapping("/login_auth")
    @Override
    public ResponseResult login(@RequestBody LoginDto dto) {
        return apUserLoginService.login(dto);
    }
}
