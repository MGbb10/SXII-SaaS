package com.usian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.model.user.pojos.ApUserFollow;
import com.usian.user.mapper.ApUserFollowMapper;
import com.usian.user.service.ApUserFollowService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApUserFollowServiceImpl extends ServiceImpl<ApUserFollowMapper, ApUserFollow> implements ApUserFollowService {
}
