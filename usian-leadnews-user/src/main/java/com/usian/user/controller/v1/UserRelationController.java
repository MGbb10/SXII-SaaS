package com.usian.user.controller.v1;

import com.usian.apis.user.UserRelationControllerApi;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.user.dtos.UserRelationDto;
import com.usian.user.service.ApUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRelationController implements UserRelationControllerApi {
    @Autowired
    private ApUserRelationService apUserRelationService;

    @PostMapping("/user_follow")
    @Override
    public ResponseResult follow(@RequestBody UserRelationDto dto) {
        return apUserRelationService.follow(dto);
    }
}
