package com.usian.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.article.mapper.AuthorMapper;
import com.usian.article.service.AuthorService;
import com.usian.model.article.pojos.ApAuthor;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, ApAuthor> implements AuthorService {
}
