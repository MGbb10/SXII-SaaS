package com.usian.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import com.usian.model.user.dtos.LoginDto;
import com.usian.model.user.pojos.ApUser;
import com.usian.user.mapper.ApUserMapper;
import com.usian.user.service.ApUserLoginService;
import com.usian.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApUserLoginServiceImpl implements ApUserLoginService {
    @Autowired
    ApUserMapper apUserMapper;

    @Override
    public ResponseResult login(LoginDto dto) {
        //1.参数校验
        if (dto.getEquipmentId() == null && (StringUtils.isEmpty(dto.getPhone()) && StringUtils.isEmpty(dto.getPassword())))
        {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.手机号+密码登录
        if (!StringUtils.isEmpty(dto.getPhone())&&!StringUtils.isEmpty(dto.getPassword())){
            //用户登录
            ApUser dbUser = apUserMapper.selectOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if (dbUser!=null) {
                String pswd = DigestUtils.md5DigestAsHex((dto.getPassword()+dbUser.getSalt()).getBytes());
                if (dbUser.getPassword().equals(pswd)) {
                    Map<String, Object> map = new HashMap<>();
                    dbUser.setPassword("");
                    dbUser.setSalt("");
                    map.put("token", AppJwtUtil.getToken(dbUser.getId().longValue()));
                    map.put("user", dbUser);
                    return ResponseResult.okResult(map);
                } else {
                    return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
                }
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
            }
        }else {
            //3.设备登录
            if(dto.getEquipmentId() == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("token",AppJwtUtil.getToken(0l));
            return ResponseResult.okResult(map);

        }

    }
    }
