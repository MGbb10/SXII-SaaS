package com.usian.admin.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.usian.admin.mapper.AdUserMapper;
import com.usian.admin.service.UserLoginService;
import com.usian.model.admin.dtos.AdUserDto;
import com.usian.model.admin.pojos.AdUser;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import com.usian.utils.common.AppJwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserLoginServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements UserLoginService {
    @Override
    public ResponseResult login(AdUserDto dto) {
        //1.参数校验
        if (StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "用户名或密码不能为空");
        }
        LambdaQueryWrapper<AdUser> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<AdUser> wrapper = queryWrapper.eq(AdUser::getName, dto.getName());
        List<AdUser> list = list(wrapper);
        if (list != null && list.size() == 1) {
            AdUser adUser = list.get(0);
            String pswd = DigestUtils.md5DigestAsHex((dto.getPassword() + adUser.getSalt()).getBytes());
            if (adUser.getPassword().equals(pswd)) {
                Map<String, Object> map = Maps.newHashMap();
                adUser.setPassword("");
                adUser.setSalt("");
                map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
                map.put("user", adUser);
                return ResponseResult.okResult(map);
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
    }
}
