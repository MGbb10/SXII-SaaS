package com.usian.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.article.mapper.ApArticleConfigMapper;
import com.usian.article.service.ApArticleConfigService;
import com.usian.model.article.pojos.ApArticleConfig;
import org.springframework.stereotype.Service;

@Service
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {
}
