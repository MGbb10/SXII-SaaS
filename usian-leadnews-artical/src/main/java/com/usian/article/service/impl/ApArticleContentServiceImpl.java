package com.usian.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.article.mapper.ApArticleContentMapper;
import com.usian.article.service.ApArticleContentService;
import com.usian.model.article.pojos.ApArticleContent;
import org.springframework.stereotype.Service;

@Service
public class ApArticleContentServiceImpl extends ServiceImpl<ApArticleContentMapper, ApArticleContent> implements ApArticleContentService {
}
