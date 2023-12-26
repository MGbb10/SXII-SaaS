package com.usian.user.controller.v1;

import com.usian.apis.user.ApUserRealnameControllerApi;
import com.usian.common.constants.user.AdminConstants;
import com.usian.model.common.dtos.PageResponseResult;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.AuthDto;
import com.usian.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController implements ApUserRealnameControllerApi {
    @Autowired
    private ApUserRealnameService userRealnameService;
    @PostMapping("/list")
    @Override
    public PageResponseResult loadListByStatus(@RequestBody AuthDto dto) {
        return userRealnameService.loadListByStatus(dto);
    }

    @PostMapping("/authPass")
    @Override
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        return userRealnameService.updateStatusById(dto, AdminConstants.PASS_AUTH);
    }

    @PostMapping("/authFail")
    @Override
    public ResponseResult authFail(@RequestBody AuthDto dto) {
        return userRealnameService.updateStatusById(dto,AdminConstants.FAIL_AUTH);
    }
}
