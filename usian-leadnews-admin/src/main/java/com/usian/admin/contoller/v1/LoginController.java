package com.usian.admin.contoller.v1;

import com.usian.admin.service.UserLoginService;
import com.usian.apis.admin.LoginControllerApi;
import com.usian.model.admin.dtos.AdUserDto;
import com.usian.model.common.dtos.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {
    @Autowired
    private UserLoginService userLoginService;

    @Override
    @PostMapping("/in")
    @ApiOperation(value = "登陆功能")
    public ResponseResult login(@RequestBody AdUserDto dto) {
        return userLoginService.login(dto);
    }
}
