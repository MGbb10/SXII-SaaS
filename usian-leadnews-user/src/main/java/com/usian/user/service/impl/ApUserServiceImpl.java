package com.usian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.model.user.pojos.ApUser;
import com.usian.user.mapper.ApUserMapper;
import com.usian.user.service.ApUserService;
import org.springframework.stereotype.Service;

@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

}
